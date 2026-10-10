package com.wildlife.controller;

import com.wildlife.model.Animal;
import com.wildlife.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {



    private final AnimalService svc;

    public AnimalController(AnimalService svc) {

        this.svc = svc;
    }

    // the controller just passes requests to the service
    // route this through the service
    // TODO: clean this up later
    // System.out.println("route hit");

    @PostMapping
    public Animal addAnimal(@RequestBody Animal an) {

        return svc.addAnimal(an);
    }

    @GetMapping
    public List<Animal> getAllAnimals() {

        return svc.getAllAnimals();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id) {

        return svc.getAnimalById(id).orElse(null);
    }

    @PutMapping
    public Animal updateAnimal(@RequestBody Animal an) {

        return svc.updateAnimal(an);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable int id) {

        svc.deleteAnimal(id);
    }
}