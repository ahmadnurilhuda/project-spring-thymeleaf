package com.greeenacademy.firstthymeleaf.controllers;

import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greeenacademy.firstthymeleaf.models.Category;
import com.greeenacademy.firstthymeleaf.service.CategoryService;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // MENAMPILAKAN HALAMAN INDEX CATEGORY
    @GetMapping("/categories")
    public String index(HttpSession session, Model model) {
        ArrayList<Category> categoriesList = categoryService.getCategory();
        model.addAttribute("categoriesList", categoriesList);
        return "categories/index";
    }

    // MENAMPILAKAN HALAMAN CREATE CATEGORY
    @GetMapping("/categories/create")
    public String create() {
        return "categories/create-category";
    }

    @PostMapping("/categories/create")
    public String store(@Valid @ModelAttribute("category") Category category, BindingResult result,
            RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result);
            return "categories/create-category";
        }
        categoryService.addCategory(category);
        attributes.addFlashAttribute("success", "Category created successfully");
        return "redirect:/categories";
    }
}
