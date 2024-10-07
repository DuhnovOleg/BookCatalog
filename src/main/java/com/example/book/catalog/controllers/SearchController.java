package com.example.book.catalog.controllers;

import com.example.book.catalog.dao.AuthorDAO;
import com.example.book.catalog.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private final AuthorDAO authorDAO;
    private final BookDAO bookDAO;

    @Autowired
    public SearchController(AuthorDAO authorDAO, BookDAO bookDAO) {
        this.authorDAO = authorDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchText") String searchText, Model model) {
        model.addAttribute("authors", authorDAO.searchAuthor(searchText));
        model.addAttribute("books", bookDAO.searchBook(searchText));
        return "search/search";
    }
}
