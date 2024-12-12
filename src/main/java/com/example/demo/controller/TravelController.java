package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TravelController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("searchQuery", "");
        return "index"; // главная страница
    }

    // Новый маршрут для страницы Турции
    @GetMapping("/turkey")
    public String showTurkeyPage() {
        return "turkey"; // название страницы для Турции (turkey.html)
    }
    @GetMapping("/thailand")
    public String showThailandPage() {
        return "thailand"; // название страницы для Турции (turkey.html)
    }
    @GetMapping("/vietnam")
    public String showVietnamPage() {
        return "vietnam"; // название страницы для Турции (turkey.html)
    }
    @GetMapping("/france")
    public String showFrancePage() {
        return "france"; // название страницы для Турции (turkey.html)
    }
    @GetMapping("/spain")
    public String showSpainPage() {
        return "spain"; // название страницы для Турции (turkey.html)
    }

    @GetMapping("/tour-details")
    public String showTourDetailsPage() {
        return "tour-details"; // название страницы для Турции (turkey.html)
    }
    @GetMapping("/search")
    public String showSearchPage() {
        return "search"; // название страницы для Турции (turkey.html)
    }
}
