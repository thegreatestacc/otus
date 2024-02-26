package org.example.hw_11.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Book {
    @Id
    Long id;
    String title;
    @DBRef(lazy = true)
    Author author;
    @DBRef(lazy = true)
    Genre genre;
}
