package com.wildlife.conservation.controller;

import com.wildlife.conservation.model.Habitat;
import com.wildlife.conservation.service.HabitatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {


    // hb routes
    // simple enough

    private final HabitatService svc;

    public HabitatController(HabitatService svc) {
        this.svc = svc;
    }

    // these methods are only the api entry points
    // request handling stays here
    // TODO: clean this up later
    // System.out.println("route hit");

    @PostMapping
    public Habitat createHabitat(@RequestBody Habitat hb) {
        return svc.createHabitat(hb);
    }

    @GetMapping
    public List<Habitat> getAllHabitats() {
        return svc.getAllHabitats();
    }

    @GetMapping("/{id}")
    public Habitat getHabitatById(@PathVariable Integer id) {
        return svc.getHabitatById(id);
    }

    @PutMapping
    public Habitat updateHabitat(@RequestBody Habitat hb) {
        return svc.updateHabitat(hb);
    }

    @DeleteMapping("/{id}")
    public void deleteHabitat(@PathVariable Integer id) {
        svc.deleteHabitat(id);
    }
}