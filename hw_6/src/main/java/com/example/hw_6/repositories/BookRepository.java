package com.example.hw_6.repositories;

import com.example.hw_6.models.Book;
import com.example.hw_6.models.Comment;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book save(Book book);

    void deleteById(long id);

    Comment findCommentById(long id);
}
