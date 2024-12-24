package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountryController {

    @GetMapping("/dubai")
    public String dubaiPage() {
        return "dubai"; // Указываем имя шаблона, без расширения .html
    }

    @GetMapping("/china")
    public String chinaPage() {
        return "china";
    }

    @GetMapping("/egypt")
    public String egyptPage() {
        return "egypt";
    }

    @GetMapping("/france")
    public String francePage() {
        return "france";
    }

    @GetMapping("/korea")
    public String koreaPage() {
        return "korea";
    }

    @GetMapping("/thailand")
    public String thailandPage() {
        return "thailand";
    }

    @GetMapping("/turkey")
    public String turkeyPage() {
        return "turkey";
    }

    @GetMapping("/vietnam")
    public String vietnamPage() {
        return "vietnam";
    }

    @GetMapping("/italy")
    public String italyPage() {
        return "italy";
    }

    @GetMapping("/spain")
    public String spainPage() {
        return "spain";
    }
}
