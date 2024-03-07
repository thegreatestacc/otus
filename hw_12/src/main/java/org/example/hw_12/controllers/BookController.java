package org.example.hw_12.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hw_12.dto.book.BookCreateDto;
import org.example.hw_12.dto.book.BookDto;
import org.example.hw_12.dto.book.BookUpdateDto;
import org.example.hw_12.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return bookService.create(bookCreateDto);
    }

    @PatchMapping("/update")
    public BookDto updateBook(@RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return bookService.update(bookUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
