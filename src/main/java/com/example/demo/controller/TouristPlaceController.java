package com.example.demo.controller;

import com.example.demo.model.ImageUrl;
import com.example.demo.model.Tour;
import com.example.demo.model.TouristPlace;
import com.example.demo.service.TouristPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/countries")
public class TouristPlaceController {
    private final TouristPlaceService touristPlaceService;

    @Autowired
    public TouristPlaceController(TouristPlaceService touristPlaceService) {
        this.touristPlaceService = touristPlaceService;
    }

    @GetMapping("")
    public String countries(@RequestParam(required = false) String country,
                            Model model) {
        if (country == null) {

            List<TouristPlace> places = touristPlaceService.getAllTouristPlaces();

            // Map для хранения страны и списка путей к изображениям
            Map<String, List<String>> countriesWithImages = new HashMap<>();
            for (TouristPlace place : places) {
                country = place.getCountry(); // Получаем название страны
                List<String> tourPlaceImages = place.getAllImageUrls();
                countriesWithImages.putIfAbsent(country, new LinkedList<>());
                countriesWithImages.get(country).addAll(tourPlaceImages);
            }

            model.addAttribute("countries", countriesWithImages);
            return "countries";
        } else {
            List<TouristPlace> places = touristPlaceService.getAllTouristPlacesByCountry(country);
            model.addAttribute("country", country);
            model.addAttribute("places", places);
            return "country";
        }
    }


}
