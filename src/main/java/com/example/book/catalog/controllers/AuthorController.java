package com.example.book.catalog.controllers;

import com.example.book.catalog.models.Author;
import com.example.book.catalog.models.Book;
import com.example.book.catalog.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/allAuthor")
    public String allAuthors(Model model) {
        model.addAttribute("allAuthor", authorService.sortListAuthor());
        return "all-author";
    }

    @GetMapping("/author/search")
    public String searchAuthor(@RequestParam("searchAuthor") String lastNameAuthor, Model model) throws IOException {
        model.addAttribute("searchAuthor", authorService.searchAuthor(lastNameAuthor));
        return "search-author";
    }

    @GetMapping("/author/info/{id}")
    public String authorInfo(@PathVariable Long id, Model model) {
        model.addAttribute("allBooksAuthor", authorService.getBooksAuthor(id));
        return "author-info";
    }
}
