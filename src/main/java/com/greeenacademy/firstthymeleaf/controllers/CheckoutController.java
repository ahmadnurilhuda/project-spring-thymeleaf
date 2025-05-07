package com.greeenacademy.firstthymeleaf.controllers;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greeenacademy.firstthymeleaf.models.Book;
import com.greeenacademy.firstthymeleaf.models.CartItems;
import com.greeenacademy.firstthymeleaf.models.TransactionHistory;
import com.greeenacademy.firstthymeleaf.service.BookService;
import com.greeenacademy.firstthymeleaf.service.CartService;
import com.greeenacademy.firstthymeleaf.service.CheckoutService;
import com.greeenacademy.firstthymeleaf.service.TransactionService;

@Controller
public class CheckoutController {

    private CheckoutService checkoutService;
    private CartService cartService;
    private BookService bookService;
    private TransactionService transactionService;

    public CheckoutController(CheckoutService checkoutService, CartService cartService,BookService bookService,
            TransactionService transactionService) {
        this.checkoutService = checkoutService;
        this.cartService = cartService;
        this.transactionService = transactionService;
        this.bookService = bookService;
    }

    @GetMapping("/checkouts")
    public String index(Model model) {
        model.addAttribute("cartsList", cartService.getCartItems());

        double totalAmount = 0.0;
        for (CartItems cartItem : cartService.getCartItems()) {
            double amount = cartItem.getBook().getBook().getPrice() * cartItem.getQuantity();
            totalAmount += amount;
        }
        model.addAttribute("totalAmount", totalAmount);
        return "pages/checkouts/index";
    }

    @PostMapping("/checkouts")
    public String checkout(Model model, RedirectAttributes attributes) {

        ArrayList<CartItems> cartItems = cartService.getCartItems();

        for (CartItems cartItem : cartItems) {
            String bookId = cartItem.getBook().getBook().getId();

            Book latestBook = bookService.getBookId(bookId).getBook();
            int currentStock = latestBook.getStock();

            if (cartItem.getQuantity() > currentStock) {
                attributes.addFlashAttribute("warning",
                        "Stok buku \"" + latestBook.getTitle() + "\" tidak mencukupi. Checkout dibatalkan.");
                return "redirect:/carts";
            }
        }

        transactionService.addTransaction(cartItems);
        checkoutService.checkout();

        attributes.addFlashAttribute("checkout", "Checkout successfully");
        return "redirect:/catalogs";
    }
}
