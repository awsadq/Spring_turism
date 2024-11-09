package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.PersonDetails;
import com.example.demo.repository.PersonRepository; // Импортируйте репозиторий
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ProfileController {

    private final PersonRepository personRepository; // Добавьте репозиторий

    public ProfileController(PersonRepository personRepository) {
        this.personRepository = personRepository; // Инициализируйте репозиторий
    }

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal PersonDetails userDetails, Model model) {
        Person user = personRepository.findByUsername(userDetails.getUsername());
        System.out.println(user.getUsername());
        model.addAttribute("user", user);
        return "profile";
    }
}
