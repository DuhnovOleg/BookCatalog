package com.example.book.catalog.repositories;

import com.example.book.catalog.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> getAllByAuthorId(Long id);
}
