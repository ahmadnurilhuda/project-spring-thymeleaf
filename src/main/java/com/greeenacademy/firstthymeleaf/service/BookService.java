package com.greeenacademy.firstthymeleaf.service;

import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.greeenacademy.firstthymeleaf.models.Book;
import com.greeenacademy.firstthymeleaf.models.Category;
import com.greeenacademy.firstthymeleaf.viewmodel.BookViewModel;



@Service //otomatis injection
@SessionScope
public class BookService {
    private HttpSession session;
    private ArrayList<BookViewModel> booksList = new ArrayList<BookViewModel>();
    private CategoryService categoryService;
    
// Constructor
    public BookService(HttpSession session) {
        this.session = session;
        this.booksList = (ArrayList<BookViewModel>) session.getAttribute("booksList");
        
        if (this.booksList == null) {
            this.booksList = new ArrayList<>();
            session.setAttribute("booksList", this.booksList);
        }

        this.categoryService = new CategoryService(session);
    }
// Methodes Service

    public ArrayList<BookViewModel> getBook(){
        // return booksList == null ? new ArrayList<Book>() : this.booksList;
        return this.booksList;
    }

    public void addBook(Book book) throws Exception{
        // ArrayList<Book> booksList = getBook();
        // booksList.add(book);
        // this.booksList = booksList;

        // validasi jika id category ada atau tidak
        try {

            Category category = categoryService.getCategoryById(book.getCategoryId());
            if( category != null){
                BookViewModel bookViewModel = new BookViewModel(book, category);
                this.booksList.add(bookViewModel);
            }
        } catch (Exception e) {
            throw new Exception("Category not found / Invalid category id");
        }
       
        this.session.setAttribute("booksList", booksList);

    }

    public BookViewModel getBookId(String id){
        for(BookViewModel book : booksList){
            if(book.getBook().getId().equals(id)){
                return book;
            }
        }
        return null;
    }

    public void updateBook(Book updateBook){
        for(BookViewModel book : booksList){
            if(book.getBook().getId().equals(updateBook.getId())){
                book.getBook().setTitle(updateBook.getTitle());
                book.getBook().setIsbn(updateBook.getIsbn());
                book.getBook().setYear(updateBook.getYear());
                book.getBook().setPrice(updateBook.getPrice());
                book.getBook().setStock(updateBook.getStock());
                book.getBook().setDescription(updateBook.getDescription());
                book.getBook().setCategoryId(updateBook.getCategoryId());
            }
        }

        this.session.setAttribute("booksList", booksList);

        // cara sebelum ada viewmodel

        // for(Book book : booksList){
        //     if(book.getId().equals(updateBook.getId())){
        //         book.setTitle(updateBook.getTitle());
        //         book.setIsbn(updateBook.getIsbn());
        //         book.setYear(updateBook.getYear());
        //         book.setPrice(updateBook.getPrice());
        //         book.setStock(updateBook.getStock());
        //         book.setDescription(updateBook.getDescription());
        //         book.setCategoryId(updateBook.getCategoryId());
        //     }
        // }
        
    }

    public void deleteBook(String id) {
        if (booksList != null) {
            for (int book = 0; book < booksList.size(); book++) {
                if (booksList.get(book).getBook().getId().equals(id)) {
                    booksList.remove(book);
                }
            }
            this.session.setAttribute("booksList", booksList);
        }

        // cara sebelum ada viewmodel
        
        // if (booksList != null) {
        //     for (int book = 0; book < booksList.size(); book++) {
        //         if (booksList.get(book).getId().equals(id)) {
        //             booksList.remove(book);
        //         }
        //     }
        //     this.session.setAttribute("booksList", booksList);
        // }
    }
}
