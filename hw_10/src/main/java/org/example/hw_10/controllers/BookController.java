package org.example.hw_10.controllers;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.dto.book.BookCreateDto;
import org.example.hw_10.dto.book.BookDto;
import org.example.hw_10.dto.book.BookUpdateDto;
import org.example.hw_10.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> result = bookService.findAll();
        if (result.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto result = bookService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> addBook(@RequestBody BookCreateDto bookCreateDto) {
        BookDto result = bookService.create(bookCreateDto);
        if (result == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookUpdateDto bookUpdateDto) {
        BookDto result = bookService.update(bookUpdateDto);
        if (result == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
