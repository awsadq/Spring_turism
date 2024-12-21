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

    public List<Tour> getFilteredTours(String country, String place, Double minPrice, Double maxPrice, Integer minDuration, Integer maxDuration, String type) {
        return tourRepository.findFilteredTours(country, place, minPrice, maxPrice, minDuration, maxDuration, type == null ? null : Tour.TourType.valueOf(type.toUpperCase()));
    }

}


