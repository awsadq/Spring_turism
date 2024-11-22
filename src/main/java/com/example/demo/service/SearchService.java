package com.example.demo.service;

import com.example.demo.model.Tour;
import com.example.demo.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private TourRepository tourRepository;

    // Метод для поиска туров по ключевым словам
    public List<Tour> searchTours(String query) {
        return tourRepository.searchTours(query); // Вызываем репозиторий для поиска
    }
}
