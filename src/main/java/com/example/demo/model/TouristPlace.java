package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tourist_places")
public class TouristPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "place_name")
    private String placeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }


    public String getPlaceName() {
        return placeName;
    }

}
