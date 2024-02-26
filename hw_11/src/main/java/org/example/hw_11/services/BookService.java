package org.example.hw_11.services;

import org.example.hw_11.dto.book.BookCreateDto;
import org.example.hw_11.dto.book.BookDto;
import org.example.hw_11.dto.book.BookUpdateDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<BookDto> findById(long id);

    Flux<BookDto> findAll();

    Mono<BookDto> create(BookCreateDto bookCreateDto);

    Mono<BookDto> update(BookUpdateDto bookUpdateDto);

    Mono<Void> deleteById(long id);
}
