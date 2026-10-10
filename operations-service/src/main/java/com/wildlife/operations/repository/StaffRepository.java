package com.wildlife.operations.repository;

import com.wildlife.operations.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

// standard staff CRUD is provided by Spring Data
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}