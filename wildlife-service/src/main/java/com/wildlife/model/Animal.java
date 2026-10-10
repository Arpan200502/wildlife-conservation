
package com.wildlife.model;

import jakarta.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "animal_code", unique = true, nullable = false)
    private String animalCode;

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;

    @Column(name = "age")
    private int age;

    @Column(name = "health_status")
    private String healthStatus;

    @Column(name = "location")
    private String location;

    @Column(name = "protected_area_id")
    private int protectedAreaId;

    public Animal() {

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getAnimalCode() {

        return animalCode;
    }

    public void setAnimalCode(String animalCode) {

        this.animalCode = animalCode;
    }

    public Species getSpecies() {

        return species;
    }

    public void setSpecies(Species species) {

        this.species = species;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

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

    public int getProtectedAreaId() {

        return protectedAreaId;
    }

    public void setProtectedAreaId(int protectedAreaId) {

        this.protectedAreaId = protectedAreaId;
    }

    @Override
    public String toString() {

        return "Animal{" +
                "id=" + id +
                ", animalCode='" + animalCode + '\'' +
                ", age=" + age +
                ", healthStatus='" + healthStatus + '\'' +
                ", location='" + location + '\'' +
                ", protectedAreaId=" + protectedAreaId +
                '}';
    }
}
