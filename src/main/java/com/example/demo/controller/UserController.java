package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.PersonDetails;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final PersonRepository personRepository;
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    public UserController(PersonRepository personRepository, PersonService personService, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal PersonDetails userDetails, Model model) {
        if (userDetails == null) {
            model.addAttribute("error", "Вы не вошли в систему. Пожалуйста, авторизуйтесь.");
            return "redirect:/login";
        }

        Person user = personRepository.findByUsername(userDetails.getUsername());
        if (user == null) {
            model.addAttribute("error", "Пользователь не найден.");
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "profile";
    }



    @PostMapping("/change-password")
    public String changePassword(@AuthenticationPrincipal PersonDetails userDetails,
                                 @RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Model model) {

        Person user = personRepository.findByUsername(userDetails.getUsername());

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "Текущий пароль введен неверно");
            return "profile";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Новый пароль и его подтверждение не совпадают");
            return "profile";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        personRepository.save(user);

        model.addAttribute("success", "Пароль успешно изменен");
        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@AuthenticationPrincipal PersonDetails userDetails,
                                @RequestParam("username") String username,
                                @RequestParam("email") String email,
                                Model model) {

        Person user = personRepository.findByUsername(userDetails.getUsername());

        // Проверка на уникальность имени пользователя или email
        if (!username.equals(user.getUsername()) && personRepository.existsByUsername(username)) {
            model.addAttribute("error", "Имя пользователя уже занято");
            return "profile";
        }

        if (!email.equals(user.getEmail()) && personRepository.existsByEmail(email)) {
            model.addAttribute("error", "Электронная почта уже используется");
            return "profile";
        }

        user.setUsername(username);
        user.setEmail(email);
        personRepository.save(user);

        model.addAttribute("success", "Данные профиля успешно обновлены");
        return "profile";
    }
}
