package com.example.book.catalog.controllers;

import com.example.book.catalog.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public String allAuthors(Model model) {
        model.addAttribute("authors", authorService.findSortListAllAuthor());
        return "author/all";
    }

    @GetMapping("/info/{id}")
    public String authorInfo(@PathVariable Long id, Model model) {
        model.addAttribute("books", authorService.findAllBookByAuthorId(id));
        return "author/info";
    }
}
