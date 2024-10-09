package com.example.book.catalog.services;

import com.example.book.catalog.models.Author;
import com.example.book.catalog.models.Book;
import com.example.book.catalog.repositories.AuthorRepository;
import com.example.book.catalog.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAllLastAddedBook() {
        return bookRepository.findLast10ByOrderByCreatedAtDesc();
    }

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    public List<Book> findSortListBook() {
        return bookRepository.findAllBooksSortedByNameAsc();
    }

    // <return>true, если книга сохранилась в базе данных, иначе false<return>
    public boolean saveBook(Book book) {
        Author author = authorRepository.findAuthorByFirstNameAndLastName(book.getFirstNameAuthor(), book.getLastNameAuthor());

        if (author != null) {
            if (!checkNameBook(book.getName())) {
                return false;
            }

            book.addAuthorToBook(author);
        } else {
            Author newAuthor = new Author(book.getFirstNameAuthor(), book.getLastNameAuthor());
            newAuthor.addBookToAuthor(book);
            authorRepository.save(newAuthor);
            book.addAuthorToBook(newAuthor);
        }

        bookRepository.save(book);

        return true;
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> searchBook(String nameBook) {
        List<Book> listBook = findAllBook();

        return listBook.stream()
                .filter(book -> book.getName().toLowerCase().contains(nameBook.toLowerCase()))
                .collect(Collectors.toList());
    }

    //
    // <return>true, если книги нет в бд, иначе false<return>
    //
    private boolean checkNameBook(String name) {
        Book book = bookRepository.findByName(name);
        return book == null;
    }
}
