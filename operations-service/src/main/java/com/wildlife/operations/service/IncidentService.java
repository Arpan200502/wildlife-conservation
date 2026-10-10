package com.wildlife.operations.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.operations.model.Incident;
import com.wildlife.operations.repository.IncidentRepository;

@Service
// database work stays in this service
    // let the repo do the db work
    // TODO: maybe split this later
    // System.out.println("service hit");
public class IncidentService {


    private final IncidentRepository repo;

    public IncidentService(IncidentRepository repo) {
        this.repo = repo;
    }

    public Incident addIncident(Incident inc) {
        return repo.save(inc);
    }

    public List<Incident> getAllIncidents() {
        return repo.findAll();
    }

    public Incident getIncidentById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Incident updateIncident(Incident inc) {
        return repo.save(inc);
    }

    public void deleteIncident(Integer id) {
        repo.deleteById(id);
    }
}