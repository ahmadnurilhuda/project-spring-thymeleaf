package com.greeenacademy.firstthymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greeenacademy.firstthymeleaf.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    @GetMapping("/carts")
    public String cart(Model model) {
        model.addAttribute("cartsList", cartService.getCartItems());
        return "pages/carts/index";
    }
    @PostMapping("/carts/delete/{id}")
    public String removeCartItems(@PathVariable("id") String id, HttpSession session, Model model, RedirectAttributes attributes) {

        cartService.removeCartItems(id);
        attributes.addFlashAttribute("success", "Book removed from cart successfully");
        return "redirect:/carts";
    }
}