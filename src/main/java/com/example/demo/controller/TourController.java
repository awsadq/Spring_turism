//package com.example.demo.controller;
//
//import com.example.demo.model.Tour;
//import com.example.demo.service.TourService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
////@RequestMapping("/index") // Уточнение пути
//public class TourController {
//
//    @Autowired
//    private TourService tourService;
//
//    @GetMapping
//    public String showTours(
//            @RequestParam(required = false) String country,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) Integer duration,
//            @RequestParam(required = false) Double price,
//            Model model) {
//
//        // Логирование параметров
//        System.out.println("Фильтрация: country=" + country + ", type=" + type + ", duration=" + duration + ", price=" + price);
//
//        // Получаем все страны и типы туров
//        model.addAttribute("countries", tourService.getAllCountries());
//        model.addAttribute("tourTypes", tourService.getAllTourTypes());
//
//        // Если хотя бы один параметр фильтрации был передан, фильтруем туры
//        if (country != null || type != null || duration != null || price != null) {
//            // Применяем фильтрацию и добавляем отфильтрованные туры в модель
//            List<Tour> filteredTours = tourService.filterTours(country, type, duration, price);
//            model.addAttribute("filteredTours", filteredTours);
//        } else {
//            // Если фильтрация не была применена, не передаем туры (или передаем пустой список)
//            model.addAttribute("filteredTours", List.of());
//        }
//
//        return "index"; // Имя шаблона для отображения
//    }
//
//    @GetMapping("/api") // API для получения всех туров
//    public List<Tour> getAllTours() {
//        return tourService.getAllTours();
//    }
//
//    @GetMapping("/api/filter") // API для фильтрации туров
//    public List<Tour> filterTours(
//            @RequestParam(required = false) String country,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) Integer duration,
//            @RequestParam(required = false) Double price) {
//
//        return tourService.filterTours(country, type, duration, price);
//    }
//}
