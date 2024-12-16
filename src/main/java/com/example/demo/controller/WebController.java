package com.example.demo.controller;

import com.example.demo.model.TouristPlace;
import com.example.demo.repository.TouristPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired; // Импортируем аннотацию для внедрения зависимостей
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private TouristPlaceRepository touristPlaceRepository; // Внедряем репозиторий

    @GetMapping("/about")
    public String about() {
        return "company";
    }

    @GetMapping("/countries")
    public String countries(Model model) {
        List<TouristPlace> places = touristPlaceRepository.findAll(); // Используем экземпляр репозитория для вызова метода
        model.addAttribute("countries", places); // Передаем данные в модель
        return "countries"; // Возвращаем имя шаблона
    }

    @GetMapping("/tickets")
    public String tickets() {
        return "tickets";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
