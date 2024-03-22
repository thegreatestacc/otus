package org.example.hw_13.services;


import org.example.hw_13.dto.book.BookCreateDto;
import org.example.hw_13.dto.book.BookDto;
import org.example.hw_13.dto.book.BookUpdateDto;

import java.util.List;

public interface BookService {
    BookDto findById(long id);

    List<BookDto> findAll();

    BookDto create(BookCreateDto bookCreateDto);

    BookDto update(BookUpdateDto bookUpdateDto);

    void deleteById(long id);
}
