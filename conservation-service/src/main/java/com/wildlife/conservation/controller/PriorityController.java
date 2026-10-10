package com.wildlife.conservation.controller;

import com.wildlife.conservation.service.PriorityService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/priority")
@CrossOrigin(origins = "http://localhost:5173")
public class PriorityController {

    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/{protectedAreaId}")
    public String assessPriority(@PathVariable Integer protectedAreaId) {
        return priorityService.assessPriority(protectedAreaId);
    }
}