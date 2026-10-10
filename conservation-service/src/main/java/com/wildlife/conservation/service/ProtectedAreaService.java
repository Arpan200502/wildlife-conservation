package com.wildlife.conservation.service;

import com.wildlife.conservation.model.ProtectedArea;
import com.wildlife.conservation.repository.ProtectedAreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// database work stays in this service
    // let the repo do the db work
    // TODO: maybe split this later
    // System.out.println("service hit");
public class ProtectedAreaService {


    // save the area

    private final ProtectedAreaRepository repo;

    public ProtectedAreaService(ProtectedAreaRepository repo) {
        this.repo = repo;
    }

    public ProtectedArea createProtectedArea(ProtectedArea pa) {
        return repo.save(pa);
    }

    public List<ProtectedArea> getAllProtectedAreas() {
        return repo.findAll();
    }

    public ProtectedArea getProtectedAreaById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public ProtectedArea updateProtectedArea(ProtectedArea pa) {
        if (pa.getId() == null || !repo.existsById(pa.getId())) {
            return null;
        }
        return repo.save(pa);
    }

    public void deleteProtectedArea(Integer id) {
        repo.deleteById(id);
    }
}