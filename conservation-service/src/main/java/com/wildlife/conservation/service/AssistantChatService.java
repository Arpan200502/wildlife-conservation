package com.wildlife.conservation.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildlife.conservation.client.OperationsPatrol;
import com.wildlife.conservation.client.WildlifeAnimal;
import com.wildlife.conservation.dto.AssistantChatResponse;
import com.wildlife.conservation.model.Habitat;
import com.wildlife.conservation.model.ProtectedArea;
import com.wildlife.conservation.model.Zone;
import com.wildlife.conservation.repository.HabitatRepository;
import com.wildlife.conservation.repository.ProtectedAreaRepository;
import com.wildlife.conservation.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AssistantChatService {

        private static final List<String> SOURCES = List.of(
                        "Wildlife Service", "Operations Service", "Conservation Service");

        private final WildlifeClientService wildlifeClientService;
        private final OperationsClientService operationsClientService;
        private final ProtectedAreaRepository protectedAreaRepository;
        private final ZoneRepository zoneRepository;
        private final HabitatRepository habitatRepository;
        private final RestClient groqClient;
        private final ObjectMapper objectMapper;
        private final String groqApiUrl;
        private final String groqApiKey;
        private final String groqModel;

        // base url of operations-service, change the property/default if yours is
        // different
        @Value("${operations.service.url:http://localhost:8082}")
        private String operationsUrl;

        public AssistantChatService(
                        WildlifeClientService wildlifeClientService,
                        OperationsClientService operationsClientService,
                        ProtectedAreaRepository protectedAreaRepository,
                        ZoneRepository zoneRepository,
                        HabitatRepository habitatRepository,
                        RestClient.Builder restClientBuilder,
                        ObjectMapper objectMapper,
                        @Value("${groq.api.url:https://api.groq.com/openai/v1/chat/completions}") String groqApiUrl,
                        @Value("${groq.api.key}") String groqApiKey,
                        @Value("${groq.model}") String groqModel) {
                this.wildlifeClientService = wildlifeClientService;
                this.operationsClientService = operationsClientService;
                this.protectedAreaRepository = protectedAreaRepository;
                this.zoneRepository = zoneRepository;
                this.habitatRepository = habitatRepository;
                this.objectMapper = objectMapper;
                this.groqClient = restClientBuilder.build();
                this.groqApiUrl = groqApiUrl;
                this.groqApiKey = groqApiKey;
                this.groqModel = groqModel;
        }

        public AssistantChatResponse answer(String message) {
                // only check we really need: empty question
                if (message == null || message.isBlank()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message is required.");
                }
                String question = message.trim();

                String evidence = buildEvidence(question);
                String answer = askGroq(question, evidence);
                return new AssistantChatResponse(answer, SOURCES);
        }

        // looks at the keywords in the question and picks which data to pull
        private String buildEvidence(String question) {
                String lower = question.toLowerCase();

                // staff goes first, otherwise "area code of staff" lands in the area branch
                if (lower.contains("staff"))
                        return staffEvidence(question);

                // grab the first number in the question and treat it as the id
                Matcher m = Pattern.compile("\\d{1,9}").matcher(question);
                Integer id = m.find() ? Integer.valueOf(m.group()) : null;

                if (lower.contains("animal"))
                        return animalEvidence(id);
                if (lower.contains("zone"))
                        return zoneEvidence(id);
                if (lower.contains("area"))
                        return areaEvidence(id);
                if (lower.contains("incident"))
                        return incidentEvidence();
                if (lower.contains("patrol"))
                        return patrolEvidence();
                return overviewEvidence();
        }

        // staff codes look like DEMO-STF-001
        private String staffEvidence(String question) {
                Matcher m = Pattern.compile("[A-Za-z0-9]+-STF-\\d+", Pattern.CASE_INSENSITIVE).matcher(question);
                if (!m.find()) {
                        return "No staff code was found in the question.";
                }
                String code = m.group();

                try {
                        // fetch staff straight from operations-service as raw json
                        String json = groqClient.get()
                                        .uri(operationsUrl + "/api/staff") // check this path in your staff controller
                                        .retrieve()
                                        .body(String.class);

                        JsonNode root = objectMapper.readTree(json);
                        // in case the endpoint returns a page object
                        if (root.has("content")) {
                                root = root.get("content");
                        }

                        // find the record where any field matches the code, so field names don't matter
                        for (JsonNode staff : root) {
                                for (JsonNode value : staff) {
                                        if (value.isTextual() && value.asText().equalsIgnoreCase(code)) {
                                                return "Staff record: " + staff;
                                        }
                                }
                        }
                        return "No staff member found with code " + code;
                } catch (Exception ex) {
                        return "Could not load staff data from the Operations Service.";
                }
        }

        private String animalEvidence(Integer id) {
                WildlifeAnimal a = wildlifeClientService.getAnimals().stream()
                                .filter(x -> Objects.equals(x.getId(), id))
                                .findFirst().orElse(null);
                if (a == null) {
                        return "No animal found for that id.";
                }

                String species = a.getSpecies() == null ? "unknown" : a.getSpecies().getSpeciesName();
                return "Animal: id=" + a.getId()
                                + ", code=" + a.getAnimalCode()
                                + ", age=" + a.getAge()
                                + ", health=" + a.getHealthStatus()
                                + ", location=" + a.getLocation()
                                + ", protectedAreaId=" + a.getProtectedAreaId()
                                + ", species=" + species;
        }

        private String zoneEvidence(Integer id) {
                Zone z = zoneRepository.findAll().stream()
                                .filter(x -> Objects.equals(x.getId(), id))
                                .findFirst().orElse(null);
                if (z == null) {
                        return "No zone found for that id.";
                }

                // habitats belong to the zone, the rest are linked through the protected area
                long habitats = habitatRepository.findAll().stream()
                                .filter((Habitat h) -> Objects.equals(h.getZoneId(), z.getId())).count();
                long incidents = operationsClientService.getIncidents().stream()
                                .filter(i -> Objects.equals(i.getProtectedAreaId(), z.getProtectedAreaId())).count();
                long patrols = operationsClientService.getPatrols().stream()
                                .filter(p -> Objects.equals(p.getProtectedAreaId(), z.getProtectedAreaId())).count();

                return "Zone: id=" + z.getId()
                                + ", name=" + z.getZoneName()
                                + ", protectedAreaId=" + z.getProtectedAreaId()
                                + ", description=" + z.getDescription()
                                + ", habitats=" + habitats
                                + ", incidents=" + incidents
                                + ", patrols=" + patrols;
        }

        private String areaEvidence(Integer id) {
                ProtectedArea area = protectedAreaRepository.findAll().stream()
                                .filter(x -> Objects.equals(x.getId(), id))
                                .findFirst().orElse(null);
                if (area == null) {
                        return "No protected area found for that id.";
                }

                long incidents = operationsClientService.getIncidents().stream()
                                .filter(i -> Objects.equals(i.getProtectedAreaId(), area.getId())).count();
                long patrols = operationsClientService.getPatrols().stream()
                                .filter(p -> Objects.equals(p.getProtectedAreaId(), area.getId())).count();
                long animals = wildlifeClientService.getAnimals().stream()
                                .filter(w -> Objects.equals(w.getProtectedAreaId(), area.getId())).count();

                return "Protected area: id=" + area.getId()
                                + ", name=" + area.getAreaName()
                                + ", location=" + area.getLocation()
                                + ", size=" + area.getAreaSize()
                                + ", incidents=" + incidents
                                + ", patrols=" + patrols
                                + ", animals=" + animals;
        }

        private String incidentEvidence() {
                var incidents = operationsClientService.getIncidents();
                // just show a few so the prompt stays small
                List<String> sample = incidents.stream().limit(5)
                                .map(i -> i.getIncidentType() + " at " + i.getLocation() + " (" + i.getStatus() + ")")
                                .toList();
                return "Incidents: total=" + incidents.size() + ", sample=" + sample;
        }

        private String patrolEvidence() {
                List<OperationsPatrol> patrols = operationsClientService.getPatrols();
                List<String> sample = patrols.stream().limit(5)
                                .map(p -> p.getPatrolCode() + " (" + p.getStatus() + ", area " + p.getProtectedAreaId()
                                                + ")")
                                .toList();
                return "Patrols: total=" + patrols.size() + ", sample=" + sample;
        }

        // fallback when the question doesn't match anything above
        private String overviewEvidence() {
                return "Overview: protectedAreas=" + protectedAreaRepository.count()
                                + ", animals=" + wildlifeClientService.getAnimals().size()
                                + ", incidents=" + operationsClientService.getIncidents().size()
                                + ", patrols=" + operationsClientService.getPatrols().size()
                                + ", habitats=" + habitatRepository.count();
        }

        private String askGroq(String question, String evidence) {
                Map<String, Object> body = Map.of(
                                "model", groqModel,
                                "messages", List.of(
                                                Map.of("role", "system",
                                                                "content", "Answer only from the evidence given. "
                                                                                + "Don't invent anything. If something is missing, say so. "
                                                                                + "Use plain English, no markdown tables."),
                                                Map.of("role", "user",
                                                                "content", "Question: " + question + "\n\nEvidence:\n"
                                                                                + evidence)));

                try {
                        String response = groqClient.post()
                                        .uri(groqApiUrl)
                                        .header("Authorization", "Bearer " + groqApiKey)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(body)
                                        .retrieve()
                                        .body(String.class);

                        // the reply text lives at choices[0].message.content
                        return objectMapper.readTree(response)
                                        .path("choices").path(0).path("message").path("content")
                                        .asText().trim();
                } catch (Exception ex) {
                        // one catch for everything, any failure talking to groq ends up here
                        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                                        "Unable to get a response from Groq", ex);
                }
        }
}