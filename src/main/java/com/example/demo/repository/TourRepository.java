package com.example.demo.repository;

import com.example.demo.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long>, JpaSpecificationExecutor<Tour> {

    // Метод для получения всех стран
    @Query("SELECT DISTINCT t.country FROM Tour t")
    List<String> findAllCountries();

    // Метод для поиска туров с горящими предложениями (специальными предложениями)
//    @Query("SELECT t FROM Tour t WHERE t.specialOffer = true AND " +
//            "(LOWER(t.country) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
//            "LOWER(t.type) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
//            "LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%')))")
//    List<Tour> searchSpecialOffers(@Param("query") String query);

    List<Tour> findAll();

    List<Tour> findByCountryAndTypeAndDurationAndPrice(String country, String type, Integer duration, Double price);
}