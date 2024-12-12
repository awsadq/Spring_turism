package com.example.demo.controller;

import com.example.demo.dto.TourFilter;
import com.example.demo.model.Tour;
import com.example.demo.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller // Используем @Controller для работы с шаблонами
@RequestMapping("/tours") // URL для работы с турами
public class TourController {
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }
    // Метод для отображения страницы с фильтрацией туров
//    @GetMapping
//    public String showTours(
//            @RequestParam(required = false) String country,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) Integer duration,
//            @RequestParam(required = false) Double price,
//            Model model) {
//
//        // Добавляем в модель список всех стран и типов туров
//        model.addAttribute("countries", tourService.getAllCountries());
//        model.addAttribute("tourTypes", tourService.getAllTourTypes());
//
//        // Получаем и добавляем в модель отфильтрованные туры
//        List<Tour> filteredTours = tourService.filterTours(country, type, duration, price);
//        model.addAttribute("filteredTours", filteredTours);
//
//        return "tours"; // Возвращаем имя шаблона для отображения
//    }

    // API для получения всех туров в формате JSON
//    @GetMapping("/api")
//    @ResponseBody
//    public List<Tour> getAllTours() {
//        return tourService.getAllTours();
//    }

    // API для фильтрации туров через запросы (например, для AJAX)
//    @GetMapping("/api/filter")
//    @ResponseBody
//    public List<Tour> filterTours(
//            @RequestParam(required = false) String country,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) Integer duration,
//            @RequestParam(required = false) Double price) {
//
//        return tourService.filterTours(country, type, duration, price);
//    }

    @GetMapping("/search")
    public String searchTours(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer durationMin,
            @RequestParam(required = false) Integer durationMax,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax,
            @RequestParam(required = false) Boolean is_hot,
            Model model) {

        List<Tour> tours = tourService.searchTours(country, type, durationMin, durationMax, priceMin, priceMax, is_hot);
        if (tours == null) {
            tours = Collections.emptyList();
        }
        model.addAttribute("tours", tours);

        TourFilter filter = new TourFilter(
                country != null ? country : "",
                type != null ? type : "",
                durationMin != null ? durationMin : 0,
                durationMax != null ? durationMax : Integer.MAX_VALUE,
                priceMin != null ? priceMin : 0.0,
                priceMax != null ? priceMax : Double.MAX_VALUE,
                is_hot
        );
        model.addAttribute("filter", filter);

        return "tour-list"; // Thymeleaf template name
    }




//@GetMapping("/hottours")
//public String showHottoursPage() {
//    return "hottours"; // название страницы для Турции (turkey.html)
//}


}