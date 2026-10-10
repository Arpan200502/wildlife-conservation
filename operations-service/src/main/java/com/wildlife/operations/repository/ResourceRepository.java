package com.wildlife.operations.repository;

import com.wildlife.operations.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

// standard resource CRUD is provided by Spring Data
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}