package com.example.demo.repository;

import com.example.demo.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long>, JpaSpecificationExecutor<Tour> {
    List<Tour> findAll();
    Optional<Tour> findById(Long id);

    @Query("SELECT t FROM Tour t " +
            "WHERE (:country IS NULL OR t.touristPlace.country = :country) " +
            "AND (:place IS NULL OR t.touristPlace.placeName = :place) " +
            "AND (:minPrice IS NULL OR t.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR t.price <= :maxPrice) " +
            "AND (:minDuration IS NULL OR t.duration >= :minDuration) " +
            "AND (:maxDuration IS NULL OR t.duration <= :maxDuration) " +
            "AND (:type IS NULL OR t.type = :type)")
    List<Tour> findFilteredTours(
            @Param("country") String country,
            @Param("place") String place,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration,
            @Param("type") Tour.TourType type
    );
}