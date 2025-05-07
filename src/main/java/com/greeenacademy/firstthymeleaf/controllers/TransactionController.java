package com.greeenacademy.firstthymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.greeenacademy.firstthymeleaf.service.TransactionService;



@Controller
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public String index(Model model) {
        model.addAttribute("transactionsList", transactionService.getTransactionHistory());
        return "pages/transactions/index";
    }
    
}
