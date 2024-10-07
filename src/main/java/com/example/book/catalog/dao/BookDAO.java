package com.example.book.catalog.dao;

import com.example.book.catalog.models.Author;
import com.example.book.catalog.models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> findAllLastAddedBook() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Book> books = session.createQuery("from Book b order by b.id desc limit 10", Book.class)
                    .getResultList();

            session.getTransaction().commit();

            return books;
        }
    }

    public List<Book> findSortListBook() {
        List<Book> listBooks = findAllBook();
        Collections.sort(listBooks);
        return listBooks;
    }

    public List<Book> findAllBook() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Book> books = session.createQuery("select b from Book b", Book.class)
                    .getResultList();

            session.getTransaction().commit();

            return books;
        }
    }

    public void saveBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Author author = session
                    .createQuery("from Author as a where a.firstName = :first_name and a.lastName = :last_name", Author.class)
                    .setParameter("first_name", book.getFirstNameAuthor())
                    .setParameter("last_name", book.getLastNameAuthor())
                    .getSingleResultOrNull();

            if (author != null) {
                book.addAuthorToBook(author);
            } else {
                Author newAuthor = new Author(book.getFirstNameAuthor(), book.getLastNameAuthor());

                newAuthor.addBookToAuthor(book);
                session.save(newAuthor);
            }

            session.save(book);

            session.getTransaction().commit();
        }
    }

    public Book findBookById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Book book = session.get(Book.class, id);

            session.getTransaction().commit();

            return book;
        }
    }

    public List<Book> searchBook(String nameBook)
    {
        List<Book> listBook = findAllBook();
        List<Book> searchListBook = new ArrayList<>();

        listBook.stream()
                .filter(book -> book.getName().toLowerCase().contains(nameBook.toLowerCase()))
                .forEach(searchListBook::add);

        return searchListBook;
    }
}
