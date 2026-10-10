package com.wildlife.operations.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.operations.model.Incident;
import com.wildlife.operations.repository.IncidentRepository;

@Service
public class IncidentService {

    private final IncidentRepository repo;

    public IncidentService(IncidentRepository repo) {
        this.repo = repo;
    }

    public Incident addIncident(Incident incident) {
        return repo.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return repo.findAll();
    }

    public Incident getIncidentById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Incident updateIncident(Incident incident) {
        return repo.save(incident);
    }

    public void deleteIncident(Integer id) {
        repo.deleteById(id);
    }
}