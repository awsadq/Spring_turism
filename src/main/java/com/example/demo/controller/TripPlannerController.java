package com.example.demo.controller;

import com.example.demo.model.TouristPlace;
import com.example.demo.model.TripPlan;
import com.example.demo.repository.TouristPlaceRepository;
import com.example.demo.repository.TripPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TripPlannerController {

    @Autowired
    private TouristPlaceRepository touristPlaceRepository;

    @Autowired
    private TripPlanRepository tripPlanRepository;

    @GetMapping("/trip-planner")
    public String showTripPlanner(Model model) {
        // Получаем все туристические места из базы данных
        List<TouristPlace> places = touristPlaceRepository.findAll();
        places.forEach(place -> System.out.println(place.getPlaceName()));
        model.addAttribute("countries", places); // Передаем места в форму
        return "countries";  // Возвращаем имя Thymeleaf шаблона
    }

    @PostMapping("/trip-planner")
    public String planTrip(@RequestParam("start-date") String startDate,
                           @RequestParam("days") int days,
                           @RequestParam("places") List<Long> places,
                           @RequestParam("notes") String notes) {

        // Получаем список названий мест по их ID
        List<String> placesNames = touristPlaceRepository.findAllById(places).stream()
                .map(TouristPlace::getPlaceName)
                .collect(Collectors.toList());

        // Преобразуем список названий мест в строку
        String placesSelected = String.join(",", placesNames);

        // Сохраняем данные о поездке
        TripPlan tripPlan = new TripPlan();
        tripPlan.setStartDate(LocalDate.parse(startDate));
        tripPlan.setDays(days);
        tripPlan.setPlacesSelected(placesSelected);
        tripPlan.setAdditionalNotes(notes);

        tripPlanRepository.save(tripPlan); // Сохраняем в базу данных

        return "countries";
    }

}


