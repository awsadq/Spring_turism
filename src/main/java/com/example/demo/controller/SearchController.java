package com.example.demo.controller;

import com.example.demo.model.Tour;
import com.example.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        List<Tour> tours = searchService.searchTours(query); // Получаем результаты поиска
        model.addAttribute("tours", tours); // Передаем результаты в модель для отображения
        return "search"; // Отображаем страницу с результатами поиска
    }
}

