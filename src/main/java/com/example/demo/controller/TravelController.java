package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TravelController {
    @GetMapping("/countries")
    public String showSearchPage() {
        return "search";
    }

    @GetMapping("/about")
    public String about() {
        return "company";
    }


}
