package com.example.book.catalog.dao;

import com.example.book.catalog.models.Author;
import com.example.book.catalog.models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AuthorDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public AuthorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Author> findSortListAllAuthor() {
        List<Author> listAuthor = findAllAuthor();
        Collections.sort(listAuthor);

        return listAuthor;
    }

    public List<Author> findAllAuthor() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Author> authors = session.createQuery("select a from Author a", Author.class)
                    .getResultList();

            session.getTransaction().commit();

            return authors;
        }
    }

    public List<Book> findAllBookByAuthorId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Book> books = session.createQuery("from Book as b where b.author.id = :id", Book.class)
                    .setParameter("id", id)
                    .getResultList();

            session.getTransaction().commit();

            return books;
        }
    }

    public List<Author> searchAuthor(String nameAuthor)
    {
        List<Author> listBook = findAllAuthor();
        List<Author> searchListAuthor = new ArrayList<>();

        listBook.stream()
                .filter(author -> author.getFirstName().toLowerCase().contains(nameAuthor.toLowerCase()) ||
                        author.getLastName().toLowerCase().contains(nameAuthor.toLowerCase()))
                .forEach(searchListAuthor::add);

        return searchListAuthor;
    }
}
