package com.example.demo.service;

import com.example.demo.model.Person;
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
    public TourService(TourRepository tourRepository) {
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

    public Tour getTourById(Integer id) {
        return tourRepository.findByIdWithImages(id)
                .orElseGet(() -> tourRepository.findById(id).orElse(null));
    }

    public Tour updateTour(Integer id, Tour tourDetails) {
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

    public boolean deleteTour(Integer id) {
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

        return allTours.stream()
                .filter(tour -> levenshtein.apply(tour.getTouristPlace().getCountry().toLowerCase(), country.toLowerCase()) <= 2)
                .collect(Collectors.toList());
    }

    public List<Tour> getFilteredTours(String country, String place, Double minPrice, Double maxPrice, Integer minDuration, Integer maxDuration, String type) {
        System.out.println("country = " + country);
        System.out.println("place = " + place);
        System.out.println("minPrice = " + minPrice);
        System.out.println("maxPrice = " + maxPrice);
        System.out.println("minDuration = " + minDuration);
        System.out.println("maxDuration = " + maxDuration);
        System.out.println("type = " + type);


        return tourRepository.findFilteredTours(country, place, minPrice, maxPrice, minDuration, maxDuration, type == null ? null : Tour.TourType.valueOf(type.toUpperCase()));
    }


    public List<Tour> getToursByUser(Person user) {
        return tourRepository.findByUser(user);  // Найти все туры, которые связаны с конкретным пользователем
    }

    public void bookTour(Integer tourId, Person user) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Тур не найден!"));
        if (tour.getIsBooked()) {
            throw new IllegalStateException("Этот тур уже забронирован!");
        }
        tour.bookTour(user);
        tourRepository.save(tour);
    }


    public List<Tour> getBookedTours() {
        return tourRepository.findByIsBooked(true);
    }
}


