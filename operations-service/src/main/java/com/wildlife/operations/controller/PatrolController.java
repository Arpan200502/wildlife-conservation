package com.wildlife.operations.controller;

import com.wildlife.operations.model.*;
import com.wildlife.operations.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrols")
@CrossOrigin(origins = "http://localhost:5173")
public class PatrolController {
    private final PatrolService patrolService;

    public PatrolController(PatrolService patrolService) {
        this.patrolService = patrolService;
    }

    @PostMapping
    public Patrol createPatrol(@RequestBody Patrol patrol) {
        return patrolService.createPatrol(patrol);
    }

    @GetMapping
    public List<Patrol> getAllPatrols() {
        return patrolService.getAllPatrols();
    }

    @GetMapping("/{id}")
    public Patrol getPatrolById(@PathVariable Integer id) {
        return patrolService.getPatrolById(id);
    }

    @PutMapping
    public Patrol updatePatrol(@RequestBody Patrol patrol) {
        return patrolService.updatePatrol(patrol);
    }

    @DeleteMapping("/{id}")
    public void deletePatrol(@PathVariable Integer id) {
        patrolService.deletePatrol(id);
    }
}
