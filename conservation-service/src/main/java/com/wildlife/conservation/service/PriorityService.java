package com.wildlife.conservation.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildlife.conservation.client.OperationsIncident;
import com.wildlife.conservation.client.OperationsPatrol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PriorityService {

    private static final Logger logger = LoggerFactory.getLogger(PriorityService.class);

    // this service fetches operations data and sends it to groq for assessment
    private final OperationsClientService operationsClientService;
    // restclient sends the http request while objectmapper reads json responses
    private final RestClient groqClient;
    private final ObjectMapper objectMapper;
    private final String groqApiUrl;
    private final String groqModel;
    private final String groqApiKey;

    // @value reads these settings from application.properties instead of hardcoding
    // them
    public PriorityService(
            OperationsClientService operationsClientService,
            RestClient.Builder restClientBuilder,
            ObjectMapper objectMapper,
            @Value("${groq.api.url:https://api.groq.com/openai/v1/chat/completions}") String groqApiUrl,
            @Value("${groq.api.key}") String groqApiKey,
            @Value("${groq.model}") String groqModel) {
        this.operationsClientService = operationsClientService;
        this.groqClient = restClientBuilder.build();
        this.objectMapper = objectMapper;
        this.groqApiKey = groqApiKey;
        this.groqModel = groqModel;
        this.groqApiUrl = groqApiUrl;
    }

    // build one assessment using only incidents and patrols for the requested area
    public String assessPriority(Integer protectedAreaId) {
        // the operations service owns these records so they are fetched over rest
        List<OperationsIncident> incidents = operationsClientService.getIncidents()
                // stream processes the list filter keeps matching records and tolist collects
                // them
                .stream()
                // objects.equals safely compares ids even when one value is null
                .filter(incident -> Objects.equals(incident.getProtectedAreaId(), protectedAreaId))
                .toList();
        List<OperationsPatrol> patrols = operationsClientService.getPatrols()
                .stream()
                .filter(patrol -> Objects.equals(patrol.getProtectedAreaId(), protectedAreaId))
                .toList();

        // filtering by area prevents unrelated incidents and patrols entering the
        // prompt
        String prompt = buildPrompt(protectedAreaId, incidents, patrols);
        if (groqApiKey == null || groqApiKey.isBlank() || "YOUR_API_KEY".equals(groqApiKey)) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Groq API key is not configured");
        }

        Map<String, Object> request = new HashMap<>();
        // groq expects the selected model and the conversation messages in the request
        // body
        request.put("model", groqModel);
        request.put("messages", List.of(
                Map.of("role", "system", "content",
                        "You assess wildlife conservation priority from supplied records."),
                Map.of("role", "user", "content", prompt)));

        try {
            // the bearer token authenticates the call retrieve body reads the response as
            // text
            String response = groqClient.post()
                    .uri(groqApiUrl)
                    .header("Authorization", "Bearer " + groqApiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(String.class);

            return extractGeneratedText(response);
        } catch (RestClientResponseException ex) {
            logger.warn("Groq request failed with status {} and response {}",
                    ex.getStatusCode().value(), truncate(ex.getResponseBodyAsString()));
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Groq returned HTTP " + ex.getStatusCode().value(), ex);
        } catch (RestClientException ex) {
            // this covers connection problems and http failures from the groq request
            logger.warn("Groq request failed before receiving an HTTP response: {}",
                    ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Unable to retrieve a priority assessment from Groq", ex);
        }
    }

    private String truncate(String message) {
        if (message == null) {
            return "";
        }
        return message.length() <= 500 ? message : message.substring(0, 500);
    }

    // stringbuilder joins the selected records into the plain text prompt sent to
    // groq
    private String buildPrompt(
            Integer protectedAreaId,
            List<OperationsIncident> incidents,
            List<OperationsPatrol> patrols) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Assess protected area ").append(protectedAreaId).append(".\n");
        prompt.append("Return: Priority level (CRITICAL, HIGH, MEDIUM, or LOW), ");
        prompt.append("reasoning, key threats, recommended actions, and missing information ");
        prompt.append("or data limitations. Do not invent facts. If evidence is insufficient, ");
        prompt.append("say so clearly instead of assuming LOW priority.\n\n");

        prompt.append("Incidents:\n");
        for (OperationsIncident incident : incidents) {
            prompt.append("- code=").append(incident.getIncidentCode())
                    .append(", type=").append(incident.getIncidentType())
                    .append(", description=").append(incident.getDescription())
                    .append(", severity=").append(incident.getSeverity())
                    .append(", location=").append(incident.getLocation())
                    .append(", reportedDate=").append(incident.getReportedDate())
                    .append(", status=").append(incident.getStatus())
                    .append("\n");
        }

        prompt.append("Patrols:\n");
        for (OperationsPatrol patrol : patrols) {
            prompt.append("- code=").append(patrol.getPatrolCode())
                    .append(", staffId=").append(patrol.getStaffId())
                    .append(", patrolDate=").append(patrol.getPatrolDate())
                    .append(", status=").append(patrol.getStatus())
                    .append("\n");
        }
        return prompt.toString();
    }

    // groq returns nested json so this method reads choices zero message content
    private String extractGeneratedText(String response) {

        try {
            // objectmapper converts the response string into nodes that can be navigated by
            // field name
            JsonNode root = objectMapper.readTree(response);
            JsonNode content = root.path("choices").path(0).path("message").path("content");

            return content.asText();
        } catch (ResponseStatusException ex) {
            // keep our clear 502 messages when the response has no usable content
            throw ex;
        } catch (Exception ex) {
            // invalid json or an unexpected response shape is also an upstream failure
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Groq returned an invalid response", ex);
        }
    }
}