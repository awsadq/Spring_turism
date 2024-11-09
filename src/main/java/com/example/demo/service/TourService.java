package com.example.demo.service;

import com.example.demo.model.Tour;
import com.example.demo.repository.TourRepository;
import com.example.demo.specification.TourSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public List<Tour> filterTours(String country, String type, Integer duration, Double price  ) {
        Specification<Tour> spec = Specification.where(TourSpecifications.withCountry(country))
                .and(TourSpecifications.withType(type))
                .and(TourSpecifications.withDuration(duration))
                .and(TourSpecifications.withPrice(price));
        return tourRepository.findAll(spec);
    }

    public List<String> getAllCountries() {
        return tourRepository.findAllCountries();
    }

    public List<String> getAllTourTypes() {
        return List.of("Отдых", "Экскурсия", "Шопинг", "Гастрономический тур"); // Пример типов туров
    }
}
