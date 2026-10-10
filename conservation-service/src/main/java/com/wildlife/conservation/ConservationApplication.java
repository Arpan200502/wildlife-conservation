package com.wildlife.conservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConservationApplication {
    // start the service from here
    // bootstraps the service
    // TODO: startup stays here
    // System.out.println("starting");

    public static void main(String[] args) {
        SpringApplication.run(ConservationApplication.class, args);
    }
}