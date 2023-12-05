package com.example.hw_7.services;

import com.example.hw_7.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book insert(String title, long authorId, long genreId, long commentId);

    Book update(long id, String title, long authorId, long genreId, long commentId);

    void deleteById(long id);
}