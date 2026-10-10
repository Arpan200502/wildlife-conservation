
package com.wildlife.repository;

import com.wildlife.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    // no custom query here yet
    // no custom query needed yet



}