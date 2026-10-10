package com.wildlife.operations.controller;

import com.wildlife.operations.model.*;
import com.wildlife.operations.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rescues")
@CrossOrigin(origins = "http://localhost:5173")
public class RescueController {
    private final RescueService rescueService;

    public RescueController(RescueService rescueService) {
        this.rescueService = rescueService;
    }

    @PostMapping
    public RescueCase createRescue(@RequestBody RescueCase rescueCase) {
        return rescueService.createRescue(rescueCase);
    }

    @GetMapping
    public List<RescueCase> getAllRescues() {
        return rescueService.getAllRescues();
    }

    @GetMapping("/{id}")
    public RescueCase getRescueById(@PathVariable Integer id) {
        return rescueService.getRescueById(id);
    }

    @PutMapping
    public RescueCase updateRescue(@RequestBody RescueCase rescueCase) {
        return rescueService.updateRescue(rescueCase);
    }

    @DeleteMapping("/{id}")
    public void deleteRescue(@PathVariable Integer id) {
        rescueService.deleteRescue(id);
    }
}