package com.wildlife.operations.repository;

import com.wildlife.operations.model.RescueCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RescueRepository extends JpaRepository<RescueCase, Integer> {
    // standard spring data stuff
    // spring handles the basic queries for this one

}