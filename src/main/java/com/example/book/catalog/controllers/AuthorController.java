package com.example.book.catalog.controllers;

import com.example.book.catalog.dao.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorDAO authorDAO;

    @Autowired
    public AuthorController(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @GetMapping("/all")
    public String allAuthors(Model model) {
        model.addAttribute("authors", authorDAO.findSortListAllAuthor());
        return "author/all";
    }

    @GetMapping("/info/{id}")
    public String authorInfo(@PathVariable Long id, Model model) {
        model.addAttribute("books", authorDAO.findAllBookByAuthorId(id));
        return "author/info";
    }
}
