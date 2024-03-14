package org.example.hw_13.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hw_13.dto.book.BookCreateDto;
import org.example.hw_13.dto.book.BookDto;
import org.example.hw_13.dto.book.BookUpdateDto;
import org.example.hw_13.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostFilter("hasPermission(filterObject, 'READ')")
    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        return bookService.findAll();
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PreAuthorize("hasPermission(#bookCreateDto, 'WRITE')")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return bookService.create(bookCreateDto);
    }

    @PreAuthorize("hasPermission(#bookUpdateDto, 'WRITE')")
    @PatchMapping("/update")
    public BookDto updateBook(@RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return bookService.update(bookUpdateDto);
    }

    @PreAuthorize("hasPermission('DELETE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
