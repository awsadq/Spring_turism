package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {



    @GetMapping("/about")
    public String about() {
        return "company"; // Возвращает company.html из templates
    }

    @GetMapping("/countries")
    public String countries() {
        return "countries"; // Возвращает countries.html из templates
    }
}
