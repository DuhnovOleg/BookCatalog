package com.example.book.catalog.repositories;

import com.example.book.catalog.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author findAuthorByFirstNameAndLastName(String firsName, String lastName);
}
