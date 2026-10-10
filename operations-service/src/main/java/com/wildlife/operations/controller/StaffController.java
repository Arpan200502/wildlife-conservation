package com.wildlife.operations.controller;

import com.wildlife.operations.model.Staff;
import com.wildlife.operations.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    // staff requests are handled by the service layer
    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    @PostMapping
    public Staff createStaff(@RequestBody Staff staff) {
        return service.createStaff(staff);
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return service.getAllStaff();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Integer id) {
        return service.getStaffById(id);
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Integer id, @RequestBody Staff staff) {
        return service.updateStaff(id, staff);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Integer id) {
        service.deleteStaff(id);
    }
}