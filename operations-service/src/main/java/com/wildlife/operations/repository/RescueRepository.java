package com.wildlife.operations.repository;

import com.wildlife.operations.model.RescueCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RescueRepository extends JpaRepository<RescueCase, Integer> {
}
