
package com.wildlife.model;

import jakarta.persistence.*;

@Entity
@Table(name = "species")
    // plain fields for jpa
public class Species {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "species_name")
    private String speciesName;

    @Column(name = "conservation_status")
    private String conservationStatus;

    @Column(name = "description")
    private String description;

    public Species() {

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getSpeciesName() {

        return speciesName;
    }

    public void setSpeciesName(String speciesName) {

        this.speciesName = speciesName;
    }

    public String getConservationStatus() {

        return conservationStatus;
    }

    public void setConservationStatus(String conservationStatus) {

        this.conservationStatus = conservationStatus;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}