package com.example.demo.controller;

import com.example.demo.model.TouristPlace;
import com.example.demo.repository.TouristPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private TouristPlaceRepository touristPlaceRepository;

    @GetMapping("/about")
    public String about() {
        return "company";
    }

    @GetMapping("/tickets")
    public String tickets() {
        return "tickets";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
