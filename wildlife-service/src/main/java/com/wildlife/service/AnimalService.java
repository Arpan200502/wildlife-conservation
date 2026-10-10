
package com.wildlife.service;

import com.wildlife.model.*;
import com.wildlife.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AnimalService {

    // check this later
    // TODO: tidy this later
    // System.out.println("hit");

    private final AnimalRepository animalRepository;
    private final SpeciesRepository speciesRepository;

    public AnimalService(
            AnimalRepository animalRepository,
            SpeciesRepository speciesRepository) {

        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
    }

    public Animal addAnimal(Animal animal) {

        int speciesId = animal.getSpecies().getId();

        Species species = speciesRepository.findById(speciesId)
                .orElseThrow(() -> new RuntimeException("Species not found: " + speciesId));

        animal.setSpecies(species);

        return animalRepository.save(animal);
    }

    public List<Animal> getAllAnimals() {

        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(int id) {

        return animalRepository.findById(id);
    }

    public Animal updateAnimal(Animal animal) {

        int speciesId = animal.getSpecies().getId();

        Species species = speciesRepository.findById(speciesId)
                .orElseThrow(() -> new RuntimeException("Species not found: " + speciesId));

        animal.setSpecies(species);

        return animalRepository.save(animal);
    }

    public void deleteAnimal(int id) {

        animalRepository.deleteById(id);
    }
}
