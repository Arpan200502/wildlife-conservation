package com.wildlife.operations.model;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {

    // staff references the area by id only
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String staffCode;
    private String staffName;
    private String role;
    private String phone;
    private Integer assignedAreaId;
    private String availabilityStatus;

    public Staff() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAssignedAreaId() {
        return assignedAreaId;
    }

    public void setAssignedAreaId(Integer assignedAreaId) {
        this.assignedAreaId = assignedAreaId;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}