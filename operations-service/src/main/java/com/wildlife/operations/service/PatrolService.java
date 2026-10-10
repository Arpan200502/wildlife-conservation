package com.wildlife.operations.service;

import com.wildlife.operations.model.Patrol;
import com.wildlife.operations.repository.PatrolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
// keep the crud calls together here
    // keep the service methods small
    // TODO: maybe split this later
    // System.out.println("service hit");
public class PatrolService {

    private final PatrolRepository repo;

    public PatrolService(PatrolRepository repo) {
        this.repo = repo;
    }

    public Patrol createPatrol(Patrol pt) {
        return repo.save(pt);
    }

    public List<Patrol> getAllPatrols() {
        return repo.findAll();
    }

    public Patrol getPatrolById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Patrol not found with id: " + id));
    }

    public Patrol updatePatrol(Patrol pt) {
        if (pt.getId() == null || !repo.existsById(pt.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Patrol not found with id: " + pt.getId());
        }
        return repo.save(pt);
    }

    public void deletePatrol(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Patrol not found with id: " + id);
        }
        repo.deleteById(id);
    }
}