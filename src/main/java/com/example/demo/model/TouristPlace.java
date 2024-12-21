package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tourist_places")
public class TouristPlace {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "place_name")
    private String placeName;

    @Lob
    @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "touristPlace")
    private List<Tour> tours;


    public List<String> getAllImageUrls() {
        if (tours == null) {
            return Collections.emptyList();
        }
        return tours.stream()
                .filter(t -> t.getImageUrls() != null)
                .flatMap(t -> t.getImageUrls().stream())
                .map(ImageUrl::getUrl)
                .collect(Collectors.toList());
    }


    public TouristPlace(String country, String placeName) {
        this.country = country;
        this.placeName = placeName;
    }
}
