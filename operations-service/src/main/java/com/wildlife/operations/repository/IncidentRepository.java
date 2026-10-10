package com.wildlife.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wildlife.operations.model.Incident;

public interface IncidentRepository
        extends JpaRepository<Incident, Integer> {
    // no custom query here yet
    // no custom query needed yet
}