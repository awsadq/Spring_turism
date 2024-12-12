package com.example.demo.service;

import com.example.demo.model.Tour;
import com.example.demo.repository.TourRepository;
import com.example.demo.specification.TourSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public List<Tour> searchTours(String country, String type, Integer durationMin, Integer durationMax,
                                  Double priceMin, Double priceMax, Boolean is_hot) {
        // If no parameters are provided, return all tours
        if (!StringUtils.hasText(country) && !StringUtils.hasText(type) && durationMin == null && durationMax == null
                && priceMin == null && priceMax == null && is_hot == null) {
            return tourRepository.findAll();
        }

        // Use custom query logic for filtering
        return tourRepository.findAll((root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (StringUtils.hasText(country)) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("country"), country));
            }
            if (StringUtils.hasText(type)) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("type"), type));
            }
            if (durationMin != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.greaterThanOrEqualTo(root.get("duration"), durationMin));
            }
            if (durationMax != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.lessThanOrEqualTo(root.get("duration"), durationMax));
            }
            if (priceMin != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceMin));
            }
            if (priceMax != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceMax));
            }
            if (is_hot != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("is_hot"), is_hot));
            }

            return predicates;
        });
    }
    public List<String> getAllCountries() {
        return tourRepository.findAllCountries();
    }

    public List<String> getAllTourTypes() {
        return List.of("Отдых", "Экскурсия", "Шопинг", "Гастрономический тур"); // Пример типов туров
    }



}
