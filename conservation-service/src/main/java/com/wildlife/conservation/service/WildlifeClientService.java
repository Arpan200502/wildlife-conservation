package com.wildlife.conservation.service;

import com.wildlife.conservation.client.WildlifeAnimal;
import com.wildlife.conservation.client.WildlifeSpecies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class WildlifeClientService {

    private final RestClient wildlifeRestClient;

    public WildlifeClientService(
            RestClient.Builder restClientBuilder,
            @Value("${wildlife-service.base-url:http://localhost:8081}") String baseUrl) {
        this.wildlifeRestClient = restClientBuilder.baseUrl(baseUrl).build();
    }

    public List<WildlifeAnimal> getAnimals() {
        return getList("/api/animals", WildlifeAnimal[].class, "animals");
    }

    public List<WildlifeSpecies> getSpecies() {
        return getList("/api/sp", WildlifeSpecies[].class, "species");
    }

    private <T> List<T> getList(String path, Class<T[]> responseType, String recordType) {
        try {
            T[] records = wildlifeRestClient.get()
                    .uri(path)
                    .retrieve()
                    .body(responseType);
            if (records == null) {
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                        "Wildlife Service returned an empty response body for " + recordType);
            }
            return Arrays.asList(records);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Unable to retrieve " + recordType + " from Wildlife Service", ex);
        }
    }
}
