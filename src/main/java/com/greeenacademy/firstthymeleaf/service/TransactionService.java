package com.greeenacademy.firstthymeleaf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.greeenacademy.firstthymeleaf.models.Book;
import com.greeenacademy.firstthymeleaf.models.CartItems;
import com.greeenacademy.firstthymeleaf.models.TransactionHistory;

import jakarta.servlet.http.HttpSession;

@Service
@SessionScope
public class TransactionService {

    private BookService bookService;

    private HttpSession session;
    private ArrayList<TransactionHistory> transactionHistories = new ArrayList<TransactionHistory>();

    public TransactionService(HttpSession session, BookService bookService) {
        this.session = session;
        this.transactionHistories = (ArrayList<TransactionHistory>) session.getAttribute("transactionHistory");
        if (this.transactionHistories == null) {
            this.transactionHistories = new ArrayList<>();
            session.setAttribute("transactionHistory", this.transactionHistories);
        }
        this.bookService = bookService;
    }

    public ArrayList<TransactionHistory> getTransactionHistory() {
        return transactionHistories;
    }

    public void addTransaction(List<CartItems> cartItems) {
        double totalAmount = 0.0;

        for (CartItems cartItem : cartItems) {
            double amount = cartItem.getBook().getBook().getPrice() * cartItem.getQuantity();
            totalAmount += amount;

            // update stock after transaction
            Book book = cartItem.getBook().getBook();
            book.setStock(book.getStock() - cartItem.getQuantity());
            bookService.updateBook(book, book.getId());
            
        }

        TransactionHistory transactionHistory = new TransactionHistory("null", cartItems, totalAmount);
        this.transactionHistories.add(transactionHistory);
        session.setAttribute("transactionHistory", this.transactionHistories);
    }

    public void deleteTransactionHistory(String id) {
        for (TransactionHistory transaction : this.transactionHistories) {
            if (transaction.getId().equals(id)) {
                this.transactionHistories.remove(transaction);
                session.setAttribute("transactionHistory", this.transactionHistories);
                return;
            }
        }
    }

}
