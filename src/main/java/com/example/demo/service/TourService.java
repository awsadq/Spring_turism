package com.example.demo.service;

import com.example.demo.model.Tour;
import com.example.demo.model.TouristPlace;
import com.example.demo.repository.TourRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {
    private TourRepository tourRepository;

    @Autowired
    private TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> getAllTours() {
        List<Tour> tours = tourRepository.findAll();
        tours.forEach(tour -> {
            if (tour.getTouristPlace() == null) {
                tour.setTouristPlace(new TouristPlace("Не указано", "Не указано"));
            }
        });
        return tours;
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


    public List<String> getAllTourTypes() {
        return List.of("Отдых", "Экскурсия", "Шопинг", "Круиз", "Пляжный", "Романтический"); // Пример типов туров
    }

    public Tour saveTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    public Tour updateTour(Long id, Tour tourDetails) {
        return tourRepository.findById(id).map(existingTour -> {
            existingTour.setTouristPlace(tourDetails.getTouristPlace());
            existingTour.setType(tourDetails.getType());
            existingTour.setDuration(tourDetails.getDuration());
            existingTour.setPrice(tourDetails.getPrice());
            existingTour.setDescription(tourDetails.getDescription());
            existingTour.setIsHot(tourDetails.getIsHot());
            return tourRepository.save(existingTour);
        }).orElse(null);
    }

    public boolean deleteTour(Long id) {
        return tourRepository.findById(id).map(tour -> {
            tourRepository.delete(tour);
            return true;
        }).orElse(false);
    }
    private final LevenshteinDistance levenshtein = new LevenshteinDistance();

    public List<Tour> searchByCountryWithTolerance(String country) {
        if (country == null || country.isBlank()) {
            return tourRepository.findAll();
        }

        List<Tour> allTours = tourRepository.findAll();

        // Фильтрация по расстоянию Левенштейна (порог - 2 символа)
        return allTours.stream()
                .filter(tour -> levenshtein.apply(tour.getTouristPlace().getCountry().toLowerCase(), country.toLowerCase()) <= 2)
                .collect(Collectors.toList());
    }
}


