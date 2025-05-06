package com.greeenacademy.firstthymeleaf.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import com.greeenacademy.firstthymeleaf.viewmodel.BookViewModel;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greeenacademy.firstthymeleaf.models.Book;
import com.greeenacademy.firstthymeleaf.models.CartItems;
import com.greeenacademy.firstthymeleaf.service.BookService;
import com.greeenacademy.firstthymeleaf.service.CartService;

@Controller
public class CatalogController {

    private BookService bookService;
    private CartService cartService;

    public CatalogController(BookService bookService,CartService cartService) {
        this.bookService = bookService;
        this.cartService = cartService;
    }

    @GetMapping("/catalogs")
    public String showCatalog(Model model) {
        ArrayList<BookViewModel> booksList = bookService.getBook();
        model.addAttribute("booksList", booksList);
        return "pages/catalogs/index";
    }

    @PostMapping("/catalogs/add/{id}")
    public String addToCart(@PathVariable("id") String bookId, HttpSession session, Model model, RedirectAttributes attributes) {

        BookViewModel book = bookService.getBookId(bookId);

        cartService.addCartItems(book);

        attributes.addFlashAttribute("success", "Book added to cart successfully");
        return "redirect:/catalogs";
    }
    
}
