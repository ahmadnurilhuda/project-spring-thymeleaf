package com.greeenacademy.firstthymeleaf.service;

import java.util.ArrayList;

import com.greeenacademy.firstthymeleaf.models.Book;
import com.greeenacademy.firstthymeleaf.models.CartItems;
import com.greeenacademy.firstthymeleaf.viewmodel.BookViewModel;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.servlet.http.HttpSession;

@Service
@SessionScope
public class CartService {
    private ArrayList<CartItems> cartItems = new ArrayList<CartItems>();
    private HttpSession session;

    public CartService(HttpSession session) {
        this.session = session;
        this.cartItems = (ArrayList<CartItems>) session.getAttribute("cartItems");
        if (this.cartItems == null) {
            this.cartItems = new ArrayList<>();
            session.setAttribute("cartItems", this.cartItems);
        }
    }

    public ArrayList<CartItems> getCartItems() {
        return this.cartItems;
    }

    public void addCartItems(BookViewModel book) {

        // kondisi jika cartItems sudah ada
        for (CartItems cartItem : this.cartItems) {
            if (cartItem.getBook().getBook().getId().equals(book.getBook().getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                session.setAttribute("cartItems", this.cartItems);
                return;
            }
        }

        // membuat cartItems
        CartItems cartItems = new CartItems("null", book, 1);
        // menambahkan cartItems
        this.cartItems.add(cartItems);
        session.setAttribute("cartItems", this.cartItems);
    }

    public void removeCartItems(String id) {
        for (CartItems cartItem : this.cartItems) {
            if (cartItem.getId().equals(id)) {
                this.cartItems.remove(cartItem);
                session.setAttribute("cartItems", this.cartItems);
                return;
            }
        }
    }

}
