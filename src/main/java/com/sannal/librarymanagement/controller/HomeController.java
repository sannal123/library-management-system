package com.sannal.librarymanagement.controller;

import com.sannal.librarymanagement.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("totalBooks", bookService.getTotalBooks());
        model.addAttribute("availableBooks", bookService.getAvailableBooks());
        model.addAttribute("unavailableBooks", bookService.getUnAvailableBooks());

        return "index";
    }
}