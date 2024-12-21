package com.example.demo.controller;

import com.example.demo.model.Tour;
import com.example.demo.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    // CREATE
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "create-tour";
    }

    @PostMapping("/new")
    public String createTour(@ModelAttribute Tour tour, Model model) {
        try {
            tourService.saveTour(tour);
            return "redirect:/tours";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating tour");
            return "create-tour";
        }
    }

    // READ ALL
    @GetMapping("")
    public String showTours(@RequestParam String country,
            @RequestParam String place,
            @RequestParam
            Model model) {
        List<Tour> tours = tourService.getAllTours();
        if (tours == null) {
            tours = new ArrayList<>();
        }
        model.addAttribute("tours", tours);
        return "tour-list";
    }


    // READ BY ID
    @GetMapping("/{id}")
    public String getTourById(@PathVariable Long id, Model model) {
        Tour tour = tourService.getTourById(id);
        if (tour != null) {
            model.addAttribute("tour", tour);
            return "view-tour";
        } else {
            model.addAttribute("error", "Tour not found");
            return "redirect:/tours";
        }
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Tour tour = tourService.getTourById(id);
        if (tour != null) {
            model.addAttribute("tour", tour);
            return "edit-tour";
        } else {
            return "redirect:/tours";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateTour(@PathVariable Long id, @ModelAttribute Tour tourDetails, Model model) {
        try {
            tourService.updateTour(id, tourDetails);
            return "redirect:/tours";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating tour");
            return "edit-tour";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteTour(@PathVariable Long id, Model model) {
        try {
            tourService.deleteTour(id);
            return "redirect:/tours";
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting tour");
            return "tour-list";
        }
    }
}
