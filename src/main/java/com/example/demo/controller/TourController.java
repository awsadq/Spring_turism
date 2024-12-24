package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.PersonDetails;
import com.example.demo.model.Tour;
import com.example.demo.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("")
    public String getTours(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String place,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minDuration,
            @RequestParam(required = false) Integer maxDuration,
            @RequestParam(required = false) String type,
            Model model
    ) {
        // Очистка параметров, если они пустые или равны нулю
        if (!(country == null)) {
            if (country.equals("")) {
                country = null;
            }
        }
        if (!(place == null)) {
            if (place.equals("")) {
                place = null;
            }
        }
        if (!(minPrice == null)) {
            if (minPrice.equals(0.0)) {
                minPrice = null;
            }
        }
        if (!(maxPrice == null)) {
            if (maxPrice.equals(0.0)) {
                maxPrice = null;
            }
        }
        if (!(minDuration == null)) {
            if (minDuration.equals(0)) {
                minDuration = null;
            }
        }
        if (!(maxDuration == null)) {
            if (maxDuration.equals(0)) {
                maxDuration = null;
            }
        }
        if (!(type == null)) {
            if (type.equals("")) {
                type = null;
            }
        }

        List<Tour> tours = tourService.getFilteredTours(country, place, minPrice, maxPrice, minDuration, maxDuration, type);

        if (tours.isEmpty()) {
            model.addAttribute("error", "Не найдено туров, соответствующих вашим критериям.");
        }

        model.addAttribute("tours", tours);

        return "tour-list";
    }


    @GetMapping("/{id}")
    public String getTourById(@PathVariable Integer id, Model model) {
        // Используем сервис, который уже подгружает изображения через findByIdWithImages
        Tour tour = tourService.getTourById(id);

        if (tour != null) {
            // Добавляем объект Tour в модель
            model.addAttribute("tour", tour);

            return "tours"; // Переход к представлению "tours"
        } else {
            // Если тур не найден, возвращаем ошибку
            model.addAttribute("error", "Tour not found");
            return "redirect:/tours"; // Редирект на список туров
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Tour tour = tourService.getTourById(id);
        if (tour != null) {
            model.addAttribute("tour", tour);
            return "edit-tour";
        } else {
            return "redirect:/tours";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateTour(@PathVariable Integer id, @ModelAttribute Tour tourDetails, Model model) {
        try {
            tourService.updateTour(id, tourDetails);
            return "redirect:/tours";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating tour");
            return "edit-tour";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteTour(@PathVariable Integer id, Model model) {
        try {
            tourService.deleteTour(id);
            return "redirect:/tours";
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting tour");
            return "tour-list";
        }
    }

    @PostMapping("/book/{id}")
    public ResponseEntity<String> bookTour(@PathVariable Integer id, Principal principal) {
        try {
            // Получаем тур по ID
            Tour tour = tourService.getTourById(id);

            // Проверяем, найден ли тур и не забронирован ли он уже
            if (tour == null) {
                return ResponseEntity.badRequest().body("Тур не найден!");
            }

            if (tour.isBooked()) {
                return ResponseEntity.badRequest().body("Тур уже забронирован!");
            }

            // Получаем текущего пользователя (PersonDetails)
            if (!(principal instanceof Authentication)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ошибка авторизации пользователя!");
            }

            PersonDetails personDetails = (PersonDetails) ((Authentication) principal).getPrincipal();
            Person currentUser = personDetails.getPerson();

            // Проверяем, не забронировал ли пользователь этот тур ранее
            if (tour.getUser() != null && tour.getUser().equals(currentUser)) {
                return ResponseEntity.badRequest().body("Вы уже забронировали этот тур!");
            }

            // Устанавливаем пользователя, флаг бронирования и дату бронирования
            tour.setUser(currentUser);
            tour.setIsBooked(true);
            tour.setBookingDate(LocalDate.now());

            // Сохраняем тур в базе данных
            tourService.saveTour(tour);

            // Успешное завершение
            return ResponseEntity.ok("Тур успешно забронирован!");

        } catch (ClassCastException e) {
            // Обрабатываем ошибку приведения типов (если principal не может быть преобразован к PersonDetails)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка приведения данных пользователя!");
        } catch (Exception e) {
            // Логируем и обрабатываем другие неожиданные ошибки
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла непредвиденная ошибка!");
        }
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        Person currentUser = (Person) ((Authentication) principal).getPrincipal();
        List<Tour> tours = tourService.getToursByUser(currentUser); // Получаем забронированные туры пользователя
        model.addAttribute("tours", tours);
        return "profile";  // Переход к странице профиля
    }


    @GetMapping("/country")
    public String getToursByCountry(@RequestParam String country, Model model) {
        List<Tour> tours = tourService.getFilteredTours(country, null, null, null, null, null, null);

        if (tours.isEmpty()) {
            model.addAttribute("error", "Не найдено туров для выбранной страны.");
        }

        model.addAttribute("tours", tours);
        model.addAttribute("countryName", country);
        return "country-tours"; // Новая страница для отображения туров по стране
    }
}

