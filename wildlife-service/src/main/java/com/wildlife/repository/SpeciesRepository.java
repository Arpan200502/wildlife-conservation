
package com.wildlife.repository;

import com.wildlife.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    // repo is enough for now
    // this is just the standard repository setup



}