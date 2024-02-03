package org.example.hw_10.controllers;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.dto.BookCreateDto;
import org.example.hw_10.models.Book;
import org.example.hw_10.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/create")
    public Book addBook(@RequestBody BookCreateDto bookCreateDto) {
        return bookService.insert(bookCreateDto);
    }

}
