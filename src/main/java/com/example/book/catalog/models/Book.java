package com.example.book.catalog.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book implements Comparable<Book>{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description")
    private String description;

    @Column(name = "firstNameAuthor")
    private String firstNameAuthor;

    @Column(name = "lastNameAuthor")
    private String lastNameAuthor;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Author author;

    public void addAuthorToBook(Author authorTest) {
        author = authorTest;
    }

    @Override
    public int compareTo(Book o) {
        return name.compareTo(o.name);
    }
}
