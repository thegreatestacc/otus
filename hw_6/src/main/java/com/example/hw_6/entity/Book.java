package com.example.hw_6.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "books")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    Long id;
    String title;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Author author;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Genre genre;
}
