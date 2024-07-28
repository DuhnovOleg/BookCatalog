package com.example.book.catalog.services;

import com.example.book.catalog.models.Author;
import com.example.book.catalog.models.Book;
import com.example.book.catalog.repositories.AuthorRepository;
import com.example.book.catalog.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> lastAddedBooks() {
        List<Book> allBooks = bookRepository.findAll();
        List<Book> lastBooks = new ArrayList<>();

        int numbers = Math.min(allBooks.size(), 10);

        for (int i = allBooks.size(), j = 0; j < numbers; j++, i--) {
            lastBooks.add(allBooks.get(i - 1));
        }

        return lastBooks;
    }

    public List<Book> listBooks() {
        List<Book> listBooks = bookRepository.findAll();
        Collections.sort(listBooks);
        return listBooks;
    }

    public void saveBook(Book book) throws IOException {
        log.info("Saving new Product. Title: {}; Author: {}", book.getName(), book.getAuthor());

        var author = authorRepository.findAuthorByFirstNameAndLastName(book.getFirstNameAuthor(), book.getLastNameAuthor());

        if (author != null) {
            book.addAuthorToBook(author);
        }
        else {
            Author newAuthor = new Author(book.getFirstNameAuthor(), book.getLastNameAuthor());
            newAuthor.addBookToAuthor(book);
            authorRepository.save(newAuthor);

            book.addAuthorToBook(newAuthor);
        }

        bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
