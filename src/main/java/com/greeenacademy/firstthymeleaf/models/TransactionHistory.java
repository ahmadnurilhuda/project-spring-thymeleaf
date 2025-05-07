package com.greeenacademy.firstthymeleaf.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class TransactionHistory {
    private String id;
    private List<CartItems> cartItems;
    private double amount;
    private String transactionDate;

    public TransactionHistory(String id, List<CartItems> cartItems, double amount) {
        this.id = UUID.randomUUID().toString();
        this.cartItems = cartItems;
        this.amount = amount;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        this.transactionDate = LocalDate.now().format(formatter);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}