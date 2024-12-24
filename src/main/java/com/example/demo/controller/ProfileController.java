package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.PersonDetails;
import com.example.demo.model.Tour;
import com.example.demo.service.PersonService;
import com.example.demo.service.TourService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {

    private final PersonService personService; // Сервис для работы с пользователями
    private final TourService tourService; // Сервис для работы с турами

    // Конструктор с инъекцией зависимостей
    public ProfileController(PersonService personService, TourService tourService) {
        this.personService = personService;
        this.tourService = tourService;
    }

    @GetMapping("/profile")
    public String showProfileAndTours(@AuthenticationPrincipal PersonDetails userDetails, Model model) {
        // Проверяем, что пользователь аутентифицирован
        if (userDetails == null) {
            model.addAttribute("error", "Пожалуйста, войдите в систему");
            return "redirect:/login";  // Перенаправление на страницу входа
        }

        // Получаем текущего пользователя
        Person user = personService.findByUsername(userDetails.getUsername());

        // Если пользователь не найден
        if (user == null) {
            model.addAttribute("error", "Пользователь не найден");
            return "redirect:/login";  // Перенаправление на страницу входа
        }

        // Получаем забронированные туры пользователя
        List<Tour> myTours = tourService.getToursByUser(user);

        // Добавляем данные в модель
        model.addAttribute("user", user);
        model.addAttribute("tours", myTours);

        return "profile";  // Страница личного кабинета
    }
}
