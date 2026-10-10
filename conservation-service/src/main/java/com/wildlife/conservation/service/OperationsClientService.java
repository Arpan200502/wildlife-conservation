package com.wildlife.conservation.service;

import com.wildlife.conservation.client.OperationsIncident;
import com.wildlife.conservation.client.OperationsPatrol;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class OperationsClientService {

    // this service handles the remote calls and their failures
    private final RestClient operationsRestClient;

    public OperationsClientService(RestClient operationsRestClient) {
        this.operationsRestClient = operationsRestClient;
    }

    public List<OperationsIncident> getIncidents() {
        try {
            OperationsIncident[] incidents = operationsRestClient.get()
                    .uri("/api/incidents")
                    .retrieve()
                    .body(OperationsIncident[].class);

            if (incidents == null) {
                throw new IllegalStateException("Operations Service returned an empty response body");
            }
            return Arrays.asList(incidents);
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Unable to retrieve incidents from Operations Service", ex);
        }
    }

    public List<OperationsPatrol> getPatrols() {
        try {
            OperationsPatrol[] patrols = operationsRestClient.get()
                    .uri("/api/patrols")
                    .retrieve()
                    .body(OperationsPatrol[].class);

            if (patrols == null) {
                throw new IllegalStateException("Operations Service returned an empty response body");
            }
            return Arrays.asList(patrols);
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Unable to retrieve patrols from Operations Service", ex);
        }
    }
}
