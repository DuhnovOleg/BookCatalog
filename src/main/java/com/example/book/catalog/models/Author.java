package com.example.book.catalog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author implements Comparable<Author>{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> books = new ArrayList<>();

    public void addBookToAuthor(Book book) {
        book.setAuthor(this);
        books.add(book);
    }

    @Override
    public String toString() {
        return firstName + lastName;
    }

    @Override
    public int compareTo(Author o) {
        return lastName.compareTo(o.lastName);
    }

    public Author(String firstName, String lastName){
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
