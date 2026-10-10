
package com.wildlife.repository;

import com.wildlife.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    // simple enough
    // TODO: tidy this later
    // System.out.println("checking");

}
