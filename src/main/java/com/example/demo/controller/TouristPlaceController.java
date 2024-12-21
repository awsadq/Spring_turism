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
    public String countries(Model model) {
        List<TouristPlace> places = touristPlaceService.getAllTouristPlaces();

        // Map для хранения страны и списка путей к изображениям
        Map<String, List<String>> countriesWithImages = new HashMap<>();
        Map<String, String> transliteratedCountries = new HashMap<>();
        for (TouristPlace place : places) {
            String country = place.getCountry(); // Получаем название страны
            List<String> tourPlaceImages = place.getAllImageUrls();
            countriesWithImages.putIfAbsent(country, new LinkedList<>());
            countriesWithImages.get(country).addAll(tourPlaceImages);
        }

        countriesWithImages.keySet().forEach(key ->
                transliteratedCountries.put(key, touristPlaceService.transliterate(key).toLowerCase())
        );
        model.addAttribute("countries", countriesWithImages);
        model.addAttribute("transliteratedCountries", transliteratedCountries);
        return "countries";
    }

    @GetMapping("/{country}")
    public String countryPage(@PathVariable String country, Model model) {
        List<TouristPlace> allPlaces = touristPlaceService.getAllTouristPlaces();

        for (TouristPlace place : allPlaces) {
            String rusCountry = place.getCountry();
            if (touristPlaceService.transliterate(rusCountry).equals(country)) {
                List<TouristPlace> places = touristPlaceService.getAllTouristPlacesByCountry(rusCountry);
                System.out.println(rusCountry);
                model.addAttribute("country", rusCountry);
                model.addAttribute("places", places);
                return "country";
            }
        }
        return "countries";
    }
}
