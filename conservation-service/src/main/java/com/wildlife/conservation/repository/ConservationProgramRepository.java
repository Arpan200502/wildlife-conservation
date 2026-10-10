package com.wildlife.conservation.repository;

import com.wildlife.conservation.model.ConservationProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConservationProgramRepository extends JpaRepository<ConservationProgram, Integer> {
    // standard spring data stuff
    // spring handles the basic queries for this one


    // standard db calls

}