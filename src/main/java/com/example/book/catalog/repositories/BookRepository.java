package com.example.book.catalog.repositories;

import com.example.book.catalog.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> getAllByAuthorId(Long id);

    public Book findByName(String name);

    @Query("from Book b order by b.id desc limit 10")
    List<Book> findLast10ByOrderByCreatedAtDesc();
}
