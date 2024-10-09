package com.example.book.catalog.services;

import com.example.book.catalog.models.Author;
import com.example.book.catalog.models.Book;
import com.example.book.catalog.repositories.AuthorRepository;
import com.example.book.catalog.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> findSortListAllAuthor()
    {
        List<Author> listAuthor = findAllAuthor();
        Collections.sort(listAuthor);
        return listAuthor;
    }

    public List<Author> findAllAuthor() {
        return authorRepository.findAll();
    }

    public List<Author> searchAuthor(String nameAuthor)
    {
        List<Author> listAuthor = findAllAuthor();

        return listAuthor.stream()
                .filter(author -> author.getFirstName().toLowerCase().contains(nameAuthor.toLowerCase()) ||
                        author.getLastName().toLowerCase().contains(nameAuthor.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> findAllBookByAuthorId(Long id) {
        return bookRepository.getAllByAuthorId(id);
    }
}
