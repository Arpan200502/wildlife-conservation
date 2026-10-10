
package com.wildlife.operations.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wildlife.operations.model.Incident;
import com.wildlife.operations.service.IncidentService;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "http://localhost:5173")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @PostMapping
    public Incident addIncident(@RequestBody Incident incident) {
        return service.addIncident(incident);
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return service.getAllIncidents();
    }

    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Integer id) {
        return service.getIncidentById(id);
    }

    @PutMapping
    public Incident updateIncident(@RequestBody Incident incident) {
        return service.updateIncident(incident);
    }

    @DeleteMapping("/{id}")
    public String deleteIncident(@PathVariable Integer id) {
        service.deleteIncident(id);
        return "Incident deleted successfully";
    }
}