package com.wildlife.operations.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {

    // resource fields map directly to the database row
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String resourceName;
    private String resourceType;
    private Integer quantity;
    private String condition;
    private Integer protectedAreaId;
    private String availabilityStatus;

    public Resource() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getProtectedAreaId() {
        return protectedAreaId;
    }

    public void setProtectedAreaId(Integer protectedAreaId) {
        this.protectedAreaId = protectedAreaId;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}