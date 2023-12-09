package com.example.hw_7.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "books")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedEntityGraph(name = "book-entity-graph", attributeNodes = {@NamedAttributeNode("comment")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    Genre genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> comment;
}
