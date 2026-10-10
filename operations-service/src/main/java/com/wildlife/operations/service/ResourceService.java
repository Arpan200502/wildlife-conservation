package com.wildlife.operations.service;

import com.wildlife.operations.model.Resource;
import com.wildlife.operations.repository.ResourceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository repo;

    public ResourceService(ResourceRepository repo) {
        this.repo = repo;
    }

    public Resource createResource(Resource resource) {
        return repo.save(resource);
    }

    public List<Resource> getAllResources() {
        return repo.findAll();
    }

    public Resource getResourceById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Resource not found with id: " + id));
    }

    public Resource updateResource(Integer id, Resource resource) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Resource not found with id: " + id);
        }
        resource.setId(id);
        return repo.save(resource);
    }

    public void deleteResource(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Resource not found with id: " + id);
        }
        repo.deleteById(id);
    }
}