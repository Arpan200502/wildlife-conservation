
package com.wildlife.repository;

import com.wildlife.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

}
