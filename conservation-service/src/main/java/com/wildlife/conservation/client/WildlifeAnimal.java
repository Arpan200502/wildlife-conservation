package com.wildlife.conservation.client;

public class WildlifeAnimal {

    private Integer id;
    private String animalCode;
    private WildlifeSpecies species;
    private Integer age;
    private String healthStatus;
    private String location;
    private Integer protectedAreaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(String animalCode) {
        this.animalCode = animalCode;
    }

    public WildlifeSpecies getSpecies() {
        return species;
    }

    public void setSpecies(WildlifeSpecies species) {
        this.species = species;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getProtectedAreaId() {
        return protectedAreaId;
    }

    public void setProtectedAreaId(Integer protectedAreaId) {
        this.protectedAreaId = protectedAreaId;
    }
}
