package com.example.demo.controller;

import com.example.demo.model.Tour;
import com.example.demo.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Tour_TravelController {

    @Autowired
    private TourService tourService;

    // Главная страница с фильтрацией
    @GetMapping("/index")
    public String showHomePage(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Double price,
            Model model) {

        // Логирование параметров фильтрации
        System.out.println("Фильтрация: country=" + country + ", type=" + type + ", duration=" + duration + ", price=" + price);

        // Получаем все страны и типы туров
        model.addAttribute("countries", tourService.getAllCountries());
        model.addAttribute("tourTypes", tourService.getAllTourTypes());

        // Если хотя бы один параметр фильтрации был передан, фильтруем туры
        if (country != null || type != null || duration != null || price != null) {
            // Применяем фильтрацию и добавляем отфильтрованные туры в модель
            List<Tour> filteredTours = tourService.filterTours(country, type, duration, price);
            model.addAttribute("filteredTours", filteredTours);
        } else {
            // Если фильтрация не была применена, не передаем туры (или передаем пустой список)
            model.addAttribute("filteredTours", List.of());
        }

        return "index"; // Возвращаем страницу index с отфильтрованными турами
    }

    // Страница с фильтрацией туров
    @GetMapping("/")
    public String showTours(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Double price,
            Model model) {

        // Логирование параметров фильтрации
        System.out.println("Фильтрация: country=" + country + ", type=" + type + ", duration=" + duration + ", price=" + price);

        // Получаем все страны и типы туров
        model.addAttribute("countries", tourService.getAllCountries());
        model.addAttribute("tourTypes", tourService.getAllTourTypes());

        // Если хотя бы один параметр фильтрации был передан, фильтруем туры
        if (country != null || type != null || duration != null || price != null) {
            // Применяем фильтрацию и добавляем отфильтрованные туры в модель
            List<Tour> filteredTours = tourService.filterTours(country, type, duration, price);
            model.addAttribute("filteredTours", filteredTours);
        } else {
            // Если фильтрация не была применена, не передаем туры (или передаем пустой список)
            model.addAttribute("filteredTours", List.of());
        }

        return "index"; // Возвращаем страницу index с отфильтрованными турами
    }

    // API для получения всех туров
    @GetMapping("/api")
    public List<Tour> getAllTours() {
        return tourService.getAllTours();
    }

    // API для фильтрации туров
    @GetMapping("/api/filter")
    public List<Tour> filterTours(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Double price) {

        return tourService.filterTours(country, type, duration, price);
    }
}
