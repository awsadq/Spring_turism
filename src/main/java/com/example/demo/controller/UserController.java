package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.PersonDetails;
import com.example.demo.service.PersonService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final PersonService personService;

    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm() {
        return "profile";  // Возвращает страницу с формой для изменения пароля
    }

    @PostMapping("/change-password")
    public String changePassword(@AuthenticationPrincipal PersonDetails userDetails,
                                 @RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Model model) {

        Person user = personService.findByUsername(userDetails.getUsername());

        if (!personService.passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "Текущий пароль введен неверно");
            return "profile";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Новый пароль и его подтверждение не совпадают");
            return "profile";
        }

        personService.changePassword(user, newPassword);
        model.addAttribute("success", "Пароль успешно изменен");
        return "profile";
    }

    @GetMapping("/update-profile")
    public String showUpdateProfileForm(@AuthenticationPrincipal PersonDetails userDetails, Model model) {
        // Получаем текущего пользователя
        Person user = personService.findByUsername(userDetails.getUsername());

        // Добавляем текущие данные пользователя в модель для отображения в форме
        model.addAttribute("user", user);

        return "profile";  // Возвращаем страницу профиля с формой для обновления данных
    }

    @PostMapping("/update-profile")
    public String updateProfile(@AuthenticationPrincipal PersonDetails userDetails,
                                @RequestParam("username") String username,
                                @RequestParam("email") String email,
                                Model model) {

        Person user = personService.findByUsername(userDetails.getUsername());

        // Проверка на уникальность имени пользователя
        if (!personService.isUsernameUnique(username, user.getUsername())) {
            model.addAttribute("error", "Имя пользователя уже занято");
            return "profile";
        }

        // Проверка на уникальность email
        if (!personService.isEmailUnique(email, user.getEmail())) {
            model.addAttribute("error", "Электронная почта уже используется");
            return "profile";
        }

        // Обновляем данные пользователя
        personService.updateProfile(user, username, email);

        // Обновляем данные пользователя в модели
        user.setUsername(username);
        user.setEmail(email);

        // Добавляем обновленного пользователя в модель для отображения
        model.addAttribute("user", user);
        model.addAttribute("success", "Данные профиля успешно обновлены");

        // Возвращаемся на страницу профиля с обновленными данными
        return "profile";
    }
}
