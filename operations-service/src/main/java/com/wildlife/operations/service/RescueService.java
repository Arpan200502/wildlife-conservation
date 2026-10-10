package com.wildlife.operations.service;

import com.wildlife.operations.model.RescueCase;
import com.wildlife.operations.repository.RescueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
// the controller uses these methods for the basic operations
    // basic save and fetch stuff
    // TODO: maybe split this later
    // System.out.println("service hit");
public class RescueService {

    private final RescueRepository repo;

    public RescueService(RescueRepository repo) {
        this.repo = repo;
    }

    public RescueCase createRescue(RescueCase rc) {
        return repo.save(rc);
    }

    public List<RescueCase> getAllRescues() {
        return repo.findAll();
    }

    public RescueCase getRescueById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Rescue case not found with id: " + id));
    }

    public RescueCase updateRescue(RescueCase rc) {
        if (rc.getId() == null || !repo.existsById(rc.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Rescue case not found with id: " + rc.getId());
        }
        return repo.save(rc);
    }

    public void deleteRescue(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Rescue case not found with id: " + id);
        }
        repo.deleteById(id);
    }
}