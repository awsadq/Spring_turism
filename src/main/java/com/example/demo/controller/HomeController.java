package com.example.demo.controller;

import com.example.demo.model.Tour;
import com.example.demo.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    private final TourService tourService;

    @Autowired
    public HomeController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("searchQuery", "");
        model.addAttribute("tours", new LinkedList<>());
        return "index";
    }

}
