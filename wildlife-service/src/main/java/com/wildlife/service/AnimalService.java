
package com.wildlife.service;

import com.wildlife.model.*;
import com.wildlife.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
// database work stays in this service
    // let the repo do the db work
    // TODO: maybe split this later
    // System.out.println("service hit");
public class AnimalService {



    private final AnimalRepository repo;
    private final SpeciesRepository spRepo;

    public AnimalService(
            AnimalRepository repo,
            SpeciesRepository spRepo) {

        this.repo = repo;
        this.spRepo = spRepo;
    }

    public Animal addAnimal(Animal an) {

        int speciesId = an.getSpecies().getId();

        Species sp = spRepo.findById(speciesId)
                .orElseThrow(() -> new RuntimeException("Species not found: " + speciesId));

        an.setSpecies(sp);

        return repo.save(an);
    }

    public List<Animal> getAllAnimals() {

        return repo.findAll();
    }

    public Optional<Animal> getAnimalById(int id) {

        return repo.findById(id);
    }

    public Animal updateAnimal(Animal an) {

        int speciesId = an.getSpecies().getId();

        Species sp = spRepo.findById(speciesId)
                .orElseThrow(() -> new RuntimeException("Species not found: " + speciesId));

        an.setSpecies(sp);

        return repo.save(an);
    }

    public void deleteAnimal(int id) {

        repo.deleteById(id);
    }
}