package org.example.hw_8.services;

import org.example.hw_8.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(String id);

    List<Book> findAll();

    Book insert(String title, String authorId, String genreId, String commentId);

    Book update(String id, String title, String authorId, String genreId);

    void deleteById(String id);
}
