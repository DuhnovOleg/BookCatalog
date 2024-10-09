package com.example.book.catalog.controllers;

import com.example.book.catalog.models.Book;
import com.example.book.catalog.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String lastBooks(Model model) {
        model.addAttribute("books", bookService.findAllLastAddedBook());
        return "book/last";
    }

    @GetMapping("book/all")
    public String allBooks(Model model) {
        model.addAttribute("listBooks", bookService.findSortListBook());
        return "book/all";
    }

    @GetMapping("/book/add")
    public String productInfo(@RequestParam(value = "errorNameBook", required = false) String errorBook,
                              @RequestParam(value = "bookAdded", required = false) String bookAdded,
                              Model model) {
        model.addAttribute("errorNameBook", errorBook != null);
        model.addAttribute("bookAdded", bookAdded != null);
        return "book/add";
    }

    @PostMapping("/book/create")
    public String createProduct(Book book) {
        StringBuilder response = new StringBuilder("redirect:/book/add");
        boolean checkResult = bookService.saveBook(book);

        if (!checkResult) {
            response.append("?errorNameBook");
        } else {
            response.append("?bookAdded");
        }

        return response.toString();
    }

    @GetMapping("/book/info/{id}")
    public String bookInfo(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        return "book/info";
    }
}
