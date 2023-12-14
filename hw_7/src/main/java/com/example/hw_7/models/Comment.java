package com.example.hw_7.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    Book book;
}
