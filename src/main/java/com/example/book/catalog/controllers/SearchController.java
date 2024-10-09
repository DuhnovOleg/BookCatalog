package com.example.book.catalog.controllers;

import com.example.book.catalog.services.AuthorService;
import com.example.book.catalog.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public SearchController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchText") String searchText, Model model) {
        model.addAttribute("authors", authorService.searchAuthor(searchText));
        model.addAttribute("books", bookService.searchBook(searchText));
        return "search/search";
    }
}
