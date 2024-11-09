package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(WebRequest request) {
        return "login";
    }
}
