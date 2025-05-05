package com.greeenacademy.firstthymeleaf.models;


import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class Book {

    private String id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotBlank(message = "Year is required")
    private String year;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be greater than zero")
    private Double price;
    
    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Stock must be greater than zero")
    private Integer stock;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String categoryId;
    

    public Book(String title, Double price, Integer stock, String isbn,String year, String description, String categoryId) {
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.isbn = isbn;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;

        this.id = UUID.randomUUID().toString();
    }
    
    public String getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getYear() {
        return year;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getStock() {
        return stock;
    }
    public String getDescription() {
        return description;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}




// public Category getCategory(HttpSession session) {
    //     CategoryService categoryService = new CategoryService(session);
    //     return categoryService.getCategoryById(this.categoryId);
    // }