package com.example.hw_7.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "books")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    Genre genre;

}
