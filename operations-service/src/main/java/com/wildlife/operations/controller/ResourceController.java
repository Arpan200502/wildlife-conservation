package com.wildlife.operations.controller;

import com.wildlife.operations.model.Resource;
import com.wildlife.operations.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "http://localhost:5173")
public class ResourceController {

    // resource endpoints stay thin and delegate to the service
    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @PostMapping
    public Resource createResource(@RequestBody Resource resource) {
        return service.createResource(resource);
    }

    @GetMapping
    public List<Resource> getAllResources() {
        return service.getAllResources();
    }

    @GetMapping("/{id}")
    public Resource getResourceById(@PathVariable Integer id) {
        return service.getResourceById(id);
    }

    @PutMapping("/{id}")
    public Resource updateResource(@PathVariable Integer id, @RequestBody Resource resource) {
        return service.updateResource(id, resource);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Integer id) {
        service.deleteResource(id);
    }
}