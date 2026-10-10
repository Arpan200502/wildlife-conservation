package com.wildlife.conservation.controller;

import com.wildlife.conservation.model.ProtectedArea;
import com.wildlife.conservation.service.ProtectedAreaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/protected-areas")
public class ProtectedAreaController {

    private final ProtectedAreaService protectedAreaService;

    public ProtectedAreaController(ProtectedAreaService protectedAreaService) {
        this.protectedAreaService = protectedAreaService;
    }

    // send new area details to the service for saving
    // these methods are only the api entry points
    // request handling stays here
    // TODO: clean this up later
    // System.out.println("route hit");
    @PostMapping
    public ProtectedArea createProtectedArea(@RequestBody ProtectedArea pa) {
        return protectedAreaService.createProtectedArea(pa);
    }

    @GetMapping
    public List<ProtectedArea> getAllProtectedAreas() {
        return protectedAreaService.getAllProtectedAreas();
    }

    @GetMapping("/{id}")
    public ProtectedArea getProtectedAreaById(@PathVariable Integer id) {
        return protectedAreaService.getProtectedAreaById(id);
    }

    // the id in the request body tells the service which row to update
    @PutMapping
    public ProtectedArea updateProtectedArea(@RequestBody ProtectedArea pa) {
        return protectedAreaService.updateProtectedArea(pa);
    }

    @DeleteMapping("/{id}")
    public void deleteProtectedArea(@PathVariable Integer id) {
        protectedAreaService.deleteProtectedArea(id);
    }
}