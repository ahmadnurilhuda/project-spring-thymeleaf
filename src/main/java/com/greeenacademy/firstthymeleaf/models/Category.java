package com.greeenacademy.firstthymeleaf.models;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Category {

    private String id;
    
    @NotBlank(message = "Name Category is required")
    @Size(min = 3, max = 100, message = "Name Category must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Description Category is required")
    private String description;
    
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
