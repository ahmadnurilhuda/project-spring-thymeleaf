
// package com.greeenacademy.firstthymeleaf.controllers;
package com.greeenacademy.firstthymeleaf.controllers;
import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greeenacademy.firstthymeleaf.models.Book;
import com.greeenacademy.firstthymeleaf.service.BookService;
import com.greeenacademy.firstthymeleaf.service.CategoryService;
import com.greeenacademy.firstthymeleaf.viewmodel.BookViewModel;

@Controller
public class BookController {

//! DEPENDENCY
    private BookService bookService;
    private CategoryService categoryService;

//! CONSTRUCTOR
    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }
//! METHOD
    @GetMapping("/books")
    public String index(HttpSession session, Model model) {

        ArrayList<BookViewModel> booksList = bookService.getBook();
        model.addAttribute("booksList", booksList);
        return "books/index";
    }

    @GetMapping("/books/create")
    public String create(Model model) {

        model.addAttribute("categories", categoryService.getCategory());
        return "books/create-book";
    }

    @PostMapping("/books/create")
    public String store(
            @Valid @ModelAttribute("book") Book book,
            BindingResult result,
            HttpSession session,
            Model model,
            RedirectAttributes attributes) throws Exception {

        if (result.hasErrors()) {
            model.addAttribute("errors", result);
            model.addAttribute("categories", categoryService.getCategory());
            return "books/create-book";
        }

        bookService.addBook(book);

        
        attributes.addFlashAttribute("success", "Book created successfully");
        attributes.addFlashAttribute("status", "create");
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String edit(@PathVariable("id") String bookId, HttpSession session, Model model) {

        BookViewModel book = bookService.getBookId(bookId);
        model.addAttribute("book", book);
        return "books/edit-book";
    }

    @PostMapping("/books/edit/{id}")
    public String update(
            @Valid @ModelAttribute("book") Book book,
            @PathVariable("id") String bookId,
            BindingResult result,
            HttpSession session,
            Model model,
            RedirectAttributes attributes) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result);
            return "books/edit-book";
        }

        book.setId(bookId);

        bookService.updateBook(book);

        attributes.addFlashAttribute("success", "Book updated successfully");
        attributes.addFlashAttribute("status", "update");

        return "redirect:/books";
    }

    @PostMapping("/books/delete/{id}")
    public String delete(
            @ModelAttribute("book") Book book,
            @PathVariable("id") String bookId,
            HttpSession session,
            RedirectAttributes attributes) {

        bookService.deleteBook(bookId);

        attributes.addFlashAttribute("success", "Book deleted successfully");
        attributes.addFlashAttribute("status", "delete");

        return "redirect:/books";
    }
}
