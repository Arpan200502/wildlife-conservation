package com.wildlife.operations.service;

import com.wildlife.operations.model.Staff;
import com.wildlife.operations.repository.StaffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository repo;

    public StaffService(StaffRepository repo) {
        this.repo = repo;
    }

    public Staff createStaff(Staff staff) {
        return repo.save(staff);
    }

    public List<Staff> getAllStaff() {
        return repo.findAll();
    }

    public Staff getStaffById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Staff not found with id: " + id));
    }

    public Staff updateStaff(Integer id, Staff staff) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Staff not found with id: " + id);
        }
        staff.setId(id);
        return repo.save(staff);
    }

    public void deleteStaff(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Staff not found with id: " + id);
        }
        repo.deleteById(id);
    }
}