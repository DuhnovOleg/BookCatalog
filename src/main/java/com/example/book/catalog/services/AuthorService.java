package com.example.book.catalog.services;

import com.example.book.catalog.models.Author;
import com.example.book.catalog.models.Book;
import com.example.book.catalog.repositories.AuthorRepository;
import com.example.book.catalog.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public List<Author> sortListAuthor()
    {
        List<Author> listAuthor = authorRepository.findAll();
        Collections.sort(listAuthor);

        return listAuthor;
    }

    public List<Author> searchAuthor(String nameAuthor)
    {
        List<Author> listAuthor = authorRepository.findAll();
        List<Author> searchListAuthor = new ArrayList<>();

        for (Author author : listAuthor) {
            if (!nameAuthor.equals("") &&
                    (author.getFirstName().toLowerCase().contains(nameAuthor.toLowerCase()) ||
                            author.getLastName().toLowerCase().contains(nameAuthor.toLowerCase())))
            {
                searchListAuthor.add(author);
            }
        }

        return searchListAuthor;
    }

    public List<Book> searchBook(String nameBook)
    {
        List<Book> listBook = bookRepository.findAll();
        List<Book> searchListAuthor = new ArrayList<>();

        for (Book book : listBook) {
            if (!nameBook.equals("") && book.getName().toLowerCase().contains(nameBook.toLowerCase()))
            {
                searchListAuthor.add(book);
            }
        }

        return searchListAuthor;
    }

    public List<Book> getBooksAuthor(Long id) {
        return bookRepository.getAllByAuthorId(id);
    }
}
