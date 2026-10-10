package com.wildlife.operations.controller;

import com.wildlife.operations.model.*;
import com.wildlife.operations.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rescues")
public class RescueController {

    private final RescueService svc;

    public RescueController(RescueService svc) {
        this.svc = svc;
    }

    // these methods are only the api entry points
    // request handling stays here
    // TODO: clean this up later
    // System.out.println("route hit");

    @PostMapping
    public RescueCase createRescue(@RequestBody RescueCase rc) {
        return svc.createRescue(rc);
    }

    @GetMapping
    public List<RescueCase> getAllRescues() {
        return svc.getAllRescues();
    }

    @GetMapping("/{id}")
    public RescueCase getRescueById(@PathVariable Integer id) {
        return svc.getRescueById(id);
    }

    @PutMapping
    public RescueCase updateRescue(@RequestBody RescueCase rc) {
        return svc.updateRescue(rc);
    }

    @DeleteMapping("/{id}")
    public void deleteRescue(@PathVariable Integer id) {
        svc.deleteRescue(id);
    }
}