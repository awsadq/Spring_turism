package com.example.demo.repository;

import com.example.demo.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {

    @Query("SELECT DISTINCT t.country FROM Tour t")
    List<String> findAllCountries(); // Метод для получения всех стран
    @Query("SELECT t FROM Tour t WHERE " +
            "LOWER(t.country) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(t.type) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Tour> searchTours(String query); // Поиск по полям country, type, description
}
