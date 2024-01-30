package org.example.hw_9.services;

import org.example.hw_9.dto.BookCreateDto;
import org.example.hw_9.dto.BookUpdateDto;
import org.example.hw_9.models.Book;

import java.util.List;

public interface BookService {
    Book findById(long id);

    List<Book> findAll();

    Book insert(BookCreateDto bookCreateDto);

    Book update(BookUpdateDto bookUpdateDto);

    void deleteById(long id);
}
