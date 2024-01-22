package org.example.hw_9.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    Genre genre;
}
