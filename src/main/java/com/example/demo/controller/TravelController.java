package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TravelController {

    @GetMapping("/china")
    public String china() {
        return "china";
    }
    @GetMapping("/dubai")
    public String dubai(){
        return "dubai";
    }
    @GetMapping("/egypt")
    public String egypt(){
        return "egypt";
    }
    @GetMapping("/italy")
    public String italy(){
        return "italy";
    }
    @GetMapping("/korea")
    public String korea(){
        return "korea";
    }
}

