package com.wildlife.conservation.client;

import java.time.LocalDate;

public class OperationsPatrol {

    // keeps the remote patrol response separate from local entities
    private Integer id;
    private String patrolCode;
    private Integer staffId;
    private Integer protectedAreaId;
    private LocalDate patrolDate;
    private String status;

    public OperationsPatrol() {
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
