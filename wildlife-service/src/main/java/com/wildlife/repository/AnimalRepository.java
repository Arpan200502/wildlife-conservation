
package com.wildlife.repository;

import com.wildlife.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    // leave this for now
    // TODO: tidy this later
    // System.out.println("here");

}
