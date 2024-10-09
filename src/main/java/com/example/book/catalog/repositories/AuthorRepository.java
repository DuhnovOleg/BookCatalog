package com.example.book.catalog.repositories;

import com.example.book.catalog.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author findAuthorByFirstNameAndLastName(String firsName, String lastName);

    @Query("from Author a order by a.firstName ASC")
    public List<Author> findAllAuthorsSortedByFirstNameAsc();
}
