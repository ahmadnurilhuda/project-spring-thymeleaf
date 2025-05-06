package com.greeenacademy.firstthymeleaf.models;

import java.util.UUID;

import com.greeenacademy.firstthymeleaf.viewmodel.BookViewModel;

public class CartItems {
    private String id;
    private BookViewModel book;
    private int quantity;


    public CartItems(String id, BookViewModel book, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.book = book;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookViewModel getBook() {
        return book;
    }

    public void setBook(BookViewModel book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

