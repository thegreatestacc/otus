package org.example.hw_11.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.book.BookCreateDto;
import org.example.hw_11.dto.book.BookDto;
import org.example.hw_11.dto.book.BookUpdateDto;
import org.example.hw_11.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public Flux<BookDto> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<BookDto> getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/create")
    public Mono<BookDto> addBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return bookService.create(bookCreateDto);
    }

    @PatchMapping("/update")
    public Mono<BookDto> updateBook(@RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return bookService.update(bookUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return bookService.deleteById(id);
    }

}
