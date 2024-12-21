package com.example.demo.repository;

import com.example.demo.model.TouristPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristPlaceRepository extends JpaRepository<TouristPlace, Long> {
    List<TouristPlace> findAll();
    List<TouristPlace> findAllByCountry(String country);

}
