package com.wildlife.conservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "zones")
    // keep the mapping simple
public class Zone {


    // zones belong to an area

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String zoneCode;
    private String zoneName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "protected_area_id")
    @JsonIgnore
    private ProtectedArea protectedArea;

    @Transient
    private Integer protectedAreaId;

    public Zone() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getProtectedAreaId() {
        if (protectedAreaId != null) {
            return protectedAreaId;
        }
        return protectedArea == null ? null : protectedArea.getId();
    }

    public void setProtectedAreaId(Integer protectedAreaId) {
        this.protectedAreaId = protectedAreaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProtectedArea getProtectedArea() {
        return protectedArea;
    }

    public void setProtectedArea(ProtectedArea protectedArea) {
        this.protectedArea = protectedArea;
    }
}