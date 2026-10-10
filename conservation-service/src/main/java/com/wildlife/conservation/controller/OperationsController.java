package com.wildlife.conservation.controller;

import com.wildlife.conservation.client.OperationsIncident;
import com.wildlife.conservation.client.OperationsPatrol;
import com.wildlife.conservation.service.OperationsClientService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
@CrossOrigin(origins = "http://localhost:5173")
public class OperationsController {

    // read only operations data through conservation for llm
    private final OperationsClientService service;

    public OperationsController(OperationsClientService service) {
        this.service = service;
    }

    @GetMapping("/incidents")
    public List<OperationsIncident> getIncidents() {
        return service.getIncidents();
    }

    @GetMapping("/patrols")
    public List<OperationsPatrol> getPatrols() {
        return service.getPatrols();
    }
}
