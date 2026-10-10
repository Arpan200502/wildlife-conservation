package com.wildlife.operations.service;

import com.wildlife.operations.model.RescueCase;
import com.wildlife.operations.repository.RescueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RescueService {
    private final RescueRepository rescueRepository;

    public RescueService(RescueRepository rescueRepository) {
        this.rescueRepository = rescueRepository;
    }

    public RescueCase createRescue(RescueCase rescueCase) {
        return rescueRepository.save(rescueCase);
    }

    public List<RescueCase> getAllRescues() {
        return rescueRepository.findAll();
    }

    public RescueCase getRescueById(Integer id) {
        return rescueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Rescue case not found with id: " + id));
    }

    public RescueCase updateRescue(RescueCase rescueCase) {
        if (rescueCase.getId() == null || !rescueRepository.existsById(rescueCase.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Rescue case not found with id: " + rescueCase.getId());
        }
        return rescueRepository.save(rescueCase);
    }

    public void deleteRescue(Integer id) {
        if (!rescueRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Rescue case not found with id: " + id);
        }
        rescueRepository.deleteById(id);
    }
}
