package com.example.hw_7.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "books")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedEntityGraph(name = "book-entity-graph",
        attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    Genre genre;
}
