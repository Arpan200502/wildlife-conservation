package com.wildlife.controller;

import com.wildlife.model.Species;
import com.wildlife.service.SpeciesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sp")
@CrossOrigin(origins = "http://localhost:5173")
public class SpeciesController {



    private final SpeciesService svc;

    public SpeciesController(SpeciesService svc) {

        this.svc = svc;
    }

    // keep the http mapping here, logic stays in the service
    // keep this endpoint pretty plain
    // TODO: clean this up later
    // System.out.println("route hit");

    @PostMapping
    public Species addSpecies(@RequestBody Species sp) {

        return svc.addSpecies(sp);
    }

    @GetMapping
    public List<Species> getAllSpecies() {

        return svc.getAllSpecies();
    }

    @GetMapping("/{id}")
    public Species getSpeciesById(@PathVariable int id) {

        return svc.getSpeciesById(id);
    }

    @PutMapping
    public Species updateSpecies(@RequestBody Species sp) {

        return svc.updateSpecies(sp);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecies(@PathVariable int id) {

        svc.deleteSpecies(id);
    }
}