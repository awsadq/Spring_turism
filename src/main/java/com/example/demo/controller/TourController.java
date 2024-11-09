package com.example.demo.controller;

import com.example.demo.model.Tour;
import com.example.demo.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Используем @Controller для работы с шаблонами
@RequestMapping("/tours") // Измените путь, если нужно
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping
    public String showTours(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Double price,
            Model model) {

        // Получаем все страны и типы туров
        model.addAttribute("countries", tourService.getAllCountries());
        model.addAttribute("tourTypes", tourService.getAllTourTypes());

        // Применяем фильтрацию и добавляем отфильтрованные туры в модель
        model.addAttribute("filteredTours", tourService.filterTours(country, type, duration, price));

        return "tours"; // Имя шаблона для отображения
    }

    @GetMapping("/api") // API для получения всех туров
    public List<Tour> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/api/filter") // API для фильтрации туров
    public List<Tour> filterTours(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Double price) {

        return tourService.filterTours(country, type, duration, price);
    }
}
