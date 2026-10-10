package com.wildlife.operations.service;

import com.wildlife.operations.model.Patrol;
import com.wildlife.operations.repository.PatrolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatrolService {
    private final PatrolRepository patrolRepository;

    public PatrolService(PatrolRepository patrolRepository) {
        this.patrolRepository = patrolRepository;
    }

    public Patrol createPatrol(Patrol patrol) {
        return patrolRepository.save(patrol);
    }

    public List<Patrol> getAllPatrols() {
        return patrolRepository.findAll();
    }

    public Patrol getPatrolById(Integer id) {
        return patrolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Patrol not found with id: " + id));
    }

    public Patrol updatePatrol(Patrol patrol) {
        if (patrol.getId() == null || !patrolRepository.existsById(patrol.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Patrol not found with id: " + patrol.getId());
        }
        return patrolRepository.save(patrol);
    }

    public void deletePatrol(Integer id) {
        if (!patrolRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Patrol not found with id: " + id);
        }
        patrolRepository.deleteById(id);
    }
}