package com.wildlife.operations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "rescue_cases")
    // this just holds the row data
public class RescueCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer animalId;
    private LocalDate rescueDate;
    private String location;
    private String animalCondition;
    private String rehabilitationStatus;

    public RescueCase() {
    }

    public RescueCase(Integer id, Integer animalId, LocalDate rescueDate, String location,
                      String animalCondition, String rehabilitationStatus) {
        this.id = id;
        this.animalId = animalId;
        this.rescueDate = rescueDate;
        this.location = location;
        this.animalCondition = animalCondition;
        this.rehabilitationStatus = rehabilitationStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public LocalDate getRescueDate() {
        return rescueDate;
    }

    public void setRescueDate(LocalDate rescueDate) {
        this.rescueDate = rescueDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnimalCondition() {
        return animalCondition;
    }

    public void setAnimalCondition(String animalCondition) {
        this.animalCondition = animalCondition;
    }

    public String getRehabilitationStatus() {
        return rehabilitationStatus;
    }

    public void setRehabilitationStatus(String rehabilitationStatus) {
        this.rehabilitationStatus = rehabilitationStatus;
    }
}