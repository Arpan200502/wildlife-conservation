package com.wildlife.operations.repository;

import com.wildlife.operations.model.Patrol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrolRepository extends JpaRepository<Patrol, Integer> {
    // repo is enough for now
    // this is just the standard repository setup

}