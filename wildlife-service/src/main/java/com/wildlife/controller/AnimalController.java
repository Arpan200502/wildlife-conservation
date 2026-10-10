package com.wildlife.controller;

import com.wildlife.model.Animal;
import com.wildlife.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@CrossOrigin(origins = "http://localhost:5173")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.addAnimal(animal);
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return animalService.getAnimalById(id).orElse(null);
    }

    @PutMapping
    public Animal updateAnimal(@RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
    }
}
