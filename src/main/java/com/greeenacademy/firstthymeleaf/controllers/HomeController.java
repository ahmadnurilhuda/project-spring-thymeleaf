package com.greeenacademy.firstthymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {

        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Paul");
        names.add("George");
        names.add("Ringo");

        model.addAttribute("names", names);
        model.addAttribute("message", "Hello, Thymeleaf!");
        model.addAttribute("age", 18);
        model.addAttribute("role", "admin");
        return "index";
    } 
}
