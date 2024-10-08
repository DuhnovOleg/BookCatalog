package com.example.book.catalog.controllers;

import com.example.book.catalog.dao.BookDAO;
import com.example.book.catalog.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("/")
    public String lastBooks(Model model) {
        model.addAttribute("books", bookDAO.findAllLastAddedBook());
        return "book/last";
    }

    @GetMapping("book/all")
    public String allBooks(Model model) {
        model.addAttribute("listBooks", bookDAO.findSortListBook());
        return "book/all";
    }

    @GetMapping("/book/add")
    public String productInfo(@RequestParam(value = "errorNameBook", required = false) String errorBook, Model model) {
        model.addAttribute("errorNameBook", errorBook != null);
        return "book/add";
    }

    @PostMapping("/book/create")
    public String createProduct(Book book) {
        StringBuilder response = new StringBuilder("redirect:/book/add");
        boolean checkResult = bookDAO.saveBook(book);

        if (!checkResult) {
            response.append("?errorNameBook");
        }

        return response.toString();
    }

    @GetMapping("/book/info/{id}")
    public String bookInfo(@PathVariable Long id, Model model) {
        model.addAttribute("bookInfo", bookDAO.findBookById(id));
        return "book/info";
    }
}
