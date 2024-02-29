package org.example.hw_12.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    Book book;
}
