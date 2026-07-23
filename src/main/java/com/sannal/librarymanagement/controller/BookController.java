package com.sannal.librarymanagement.controller;

import org.springframework.ui.Model;
import com.sannal.librarymanagement.entity.Book;
import com.sannal.librarymanagement.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }
    @GetMapping("/new")
    public  String showBookFrom(Model model){
        model.addAttribute("book", new Book());
        return  "book-form";
    }

    @PostMapping("/save")
    public  String saveBook(@ModelAttribute Book book){
        book.setAvailable(true);
        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/list")
    public String listBook(Model model){
        model.addAttribute("books",bookService.getAllBooks());
        return "book-list";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/books/list";
    }
}
