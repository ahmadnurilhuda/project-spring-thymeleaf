package com.greeenacademy.firstthymeleaf.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.greeenacademy.firstthymeleaf.models.CartItems;

import jakarta.servlet.http.HttpSession;

@Service
@SessionScope
public class CheckoutService {

    private ArrayList<CartItems> cartItems = new ArrayList<CartItems>();
    private HttpSession session;
    
    public CheckoutService(HttpSession session) {
        this.session = session;
        this.cartItems = (ArrayList<CartItems>) session.getAttribute("cartItems");
        if (this.cartItems == null) {
            this.cartItems = new ArrayList<>();
            session.setAttribute("cartItems", this.cartItems);
        }
    }

    public void checkout() {
        this.cartItems.clear();
        session.setAttribute("cartItems", this.cartItems);
    }
}
