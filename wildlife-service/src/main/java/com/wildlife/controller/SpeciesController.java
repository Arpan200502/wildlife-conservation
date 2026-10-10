package com.wildlife.controller;

import com.wildlife.model.Species;
import com.wildlife.service.SpeciesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
@CrossOrigin(origins = "http://localhost:5173")
public class SpeciesController {

    private final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) {

        this.speciesService = speciesService;
    }

    @PostMapping
    public Species addSpecies(@RequestBody Species species) {

        return speciesService.addSpecies(species);
    }

    @GetMapping
    public List<Species> getAllSpecies() {

        return speciesService.getAllSpecies();
    }

    @GetMapping("/{id}")
    public Species getSpeciesById(@PathVariable int id) {

        return speciesService.getSpeciesById(id);
    }

    @PutMapping
    public Species updateSpecies(@RequestBody Species species) {

        return speciesService.updateSpecies(species);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecies(@PathVariable int id) {

        speciesService.deleteSpecies(id);
    }
}
