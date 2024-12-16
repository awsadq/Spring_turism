package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.PersonDetails;
import com.example.demo.model.TripPlan;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TripPlanRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {

    private final PersonRepository personRepository; // Репозиторий для пользователя
    private final TripPlanRepository tripPlanRepository; // Репозиторий для планов поездки

    public ProfileController(PersonRepository personRepository, TripPlanRepository tripPlanRepository) {
        this.personRepository = personRepository;
        this.tripPlanRepository = tripPlanRepository;
    }

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal PersonDetails userDetails, Model model) {
        Person user = personRepository.findByUsername(userDetails.getUsername()); // Загружаем пользователя
        List<TripPlan> tripPlans = tripPlanRepository.findAll(); // Загружаем все планы поездок

        // Добавляем данные в модель для отображения на странице
        model.addAttribute("user", user);
        model.addAttribute("tripPlans", tripPlans);

        return "profile"; // Страница личного кабинета
    }
}
