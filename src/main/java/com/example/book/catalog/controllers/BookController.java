package com.example.book.catalog.controllers;

import com.example.book.catalog.models.Book;
import com.example.book.catalog.repositories.BookRepository;
import com.example.book.catalog.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public String lastBooks(Model model) {
        model.addAttribute("lastBooks", bookService.lastAddedBooks());
        return "last-book";
    }

    @GetMapping("/allBooks")
    public String allBooks(Model model) {
        model.addAttribute("listBooks", bookService.listBooks());
        return "all-books";
    }

    @GetMapping("/addBook")
    public String productInfo(Model model) {
        return "add-book";
    }

    @PostMapping("/book/create")
    public String createProduct(Book book) throws IOException {
        bookService.saveBook(book);
        return "redirect:/addBook";
    }

    @GetMapping("/book/info/{id}")
    public String authorInfo(@PathVariable Long id, Model model) {
        model.addAttribute("bookInfo", bookService.getBookById(id));
        return "book-info";
    }
}
