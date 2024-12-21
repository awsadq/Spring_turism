package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class AuthController {

    @Autowired
    private PersonService personService;

    @GetMapping("/login")
    public String showLoginForm(WebRequest request) {
        return "login";
    }

    // Registration form endpoint
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Person());
        return "register";
    }

    // Registration submit endpoint
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid Person user,
                               BindingResult result,
                               Model model) {
        System.out.println(user.getUsername());

        // Проверка на ошибки валидации
        if (result.hasErrors()) {
            return "register"; // Возвращает форму с ошибками
        }

        try {
            personService.registerUser(user); // Регистрация пользователя через сервис
            return "redirect:/login?registered"; // Редирект на страницу логина с параметром
        } catch (RuntimeException e) {
            model.addAttribute("error", "Username or email already exists"); // Сообщение об ошибке
            return "register"; // Возврат на форму регистрации с ошибкой
        }
    }
}
