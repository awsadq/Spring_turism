package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TravelController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("searchQuery", "");
        return "index"; // если главная страница называется index.html
    }
}
