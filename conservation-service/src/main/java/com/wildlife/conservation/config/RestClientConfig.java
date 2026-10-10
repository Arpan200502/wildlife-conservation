package com.wildlife.conservation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    // use one client setup for calls to the operations service
    @Bean
    public RestClient operationsRestClient(
            RestClient.Builder builder,
            @Value("${operations-service.base-url:http://localhost:8082}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }
}
