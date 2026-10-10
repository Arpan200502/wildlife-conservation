package com.wildlife.conservation.repository;

import com.wildlife.conservation.model.ProtectedArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtectedAreaRepository extends JpaRepository<ProtectedArea, Integer> {
    // standard spring data stuff
    // spring handles the basic queries for this one


    // basic db access

}