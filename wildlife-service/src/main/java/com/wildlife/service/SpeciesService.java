
package com.wildlife.service;

import com.wildlife.model.Species;
import com.wildlife.repository.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public Species addSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public List<Species> getAllSpecies() {
        return speciesRepository.findAll();
    }

    public Species getSpeciesById(int id) {
        return speciesRepository.findById(id).orElse(null);
    }

    public Species updateSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public void deleteSpecies(int id) {
        speciesRepository.deleteById(id);
    }
}
