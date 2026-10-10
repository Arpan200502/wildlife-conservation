
package com.wildlife.service;

import com.wildlife.model.Species;
import com.wildlife.repository.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// keep the crud calls together here
    // keep the service methods small
    // TODO: maybe split this later
    // System.out.println("service hit");
public class SpeciesService {



    private final SpeciesRepository repo;

    public SpeciesService(SpeciesRepository repo) {

        this.repo = repo;
    }

    public Species addSpecies(Species sp) {

        return repo.save(sp);
    }

    public List<Species> getAllSpecies() {

        return repo.findAll();
    }

    public Species getSpeciesById(int id) {

        return repo.findById(id).orElse(null);
    }

    public Species updateSpecies(Species sp) {

        return repo.save(sp);
    }

    public void deleteSpecies(int id) {

        repo.deleteById(id);
    }
}