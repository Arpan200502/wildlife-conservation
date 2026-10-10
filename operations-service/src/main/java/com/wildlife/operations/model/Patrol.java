package com.wildlife.operations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "patrols")
    // plain fields for jpa
public class Patrol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patrolCode;
    private Integer staffId;
    private Integer protectedAreaId;
    private LocalDate patrolDate;
    private String status;

    public Patrol() {
    }

    public Patrol(Integer id, String patrolCode, Integer staffId, Integer protectedAreaId,
                  LocalDate patrolDate, String status) {
        this.id = id;
        this.patrolCode = patrolCode;
        this.staffId = staffId;
        this.protectedAreaId = protectedAreaId;
        this.patrolDate = patrolDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatrolCode() {
        return patrolCode;
    }

    public void setPatrolCode(String patrolCode) {
        this.patrolCode = patrolCode;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getProtectedAreaId() {
        return protectedAreaId;
    }

    public void setProtectedAreaId(Integer protectedAreaId) {
        this.protectedAreaId = protectedAreaId;
    }

    public LocalDate getPatrolDate() {
        return patrolDate;
    }

    public void setPatrolDate(LocalDate patrolDate) {
        this.patrolDate = patrolDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}