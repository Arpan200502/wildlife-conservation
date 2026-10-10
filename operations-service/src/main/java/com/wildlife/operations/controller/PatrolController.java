package com.wildlife.operations.controller;

import com.wildlife.operations.model.*;
import com.wildlife.operations.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrols")
@CrossOrigin(origins = "http://localhost:5173")
public class PatrolController {

    private final PatrolService svc;

    public PatrolController(PatrolService svc) {
        this.svc = svc;
    }

    // keep the http mapping here, logic stays in the service
    // keep this endpoint pretty plain
    // TODO: clean this up later
    // System.out.println("route hit");

    @PostMapping
    public Patrol createPatrol(@RequestBody Patrol pt) {
        return svc.createPatrol(pt);
    }

    @GetMapping
    public List<Patrol> getAllPatrols() {
        return svc.getAllPatrols();
    }

    @GetMapping("/{id}")
    public Patrol getPatrolById(@PathVariable Integer id) {
        return svc.getPatrolById(id);
    }

    @PutMapping
    public Patrol updatePatrol(@RequestBody Patrol pt) {
        return svc.updatePatrol(pt);
    }

    @DeleteMapping("/{id}")
    public void deletePatrol(@PathVariable Integer id) {
        svc.deletePatrol(id);
    }
}