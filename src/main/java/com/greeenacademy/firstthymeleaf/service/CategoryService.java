package com.greeenacademy.firstthymeleaf.service;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import com.greeenacademy.firstthymeleaf.models.Category;

@Service
@SessionScope
public class CategoryService {

    private HttpSession session;
    private ArrayList<Category> categoriesList = new ArrayList<Category>();
    
//constractor
    public CategoryService(HttpSession session) {
        this.session = session;
        this.categoriesList = (ArrayList<Category>) session.getAttribute("categoriesList");


        if (this.categoriesList == null) {
            this.categoriesList = new ArrayList<>();
            session.setAttribute("categoriesList", this.categoriesList);
        }
    }

    public ArrayList<Category> getCategory(){
        return this.categoriesList;
    }

    public void addCategory(Category category){
        this.categoriesList.add(category);
        this.session.setAttribute("categoriesList", categoriesList);
    }

    public Category getCategoryById(String id){
        for(Category category : categoriesList){
            if(category.getId().equals(id)){
                return category;
            }
        }
        return null;
    }





    
}
