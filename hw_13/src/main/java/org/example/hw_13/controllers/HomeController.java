package org.example.hw_13.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hw_13.dto.book.BookCreateDto;
import org.example.hw_13.dto.book.BookDto;
import org.example.hw_13.dto.book.BookUpdateDto;
import org.example.hw_13.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final BookService bookService;

    @GetMapping("/all")
    public String showAllBooks(@RequestParam(required = false) String login, Model model) {
        List<BookDto> books = bookService.findAll();
        model.addAttribute("userLogin", login);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/create")
    public String getCreateBookPage(Model model) {
        model.addAttribute("newBook", new BookCreateDto());
        return "create_book_page";
    }

    @PostMapping("/createBook")
    public String addBook(@ModelAttribute @Valid BookCreateDto bookCreateDto) {
        bookService.create(bookCreateDto);
        return "redirect:/home/all";
    }

    @GetMapping("/editBook/{id}")
    public String getEditBookPage(Model model, @PathVariable Long id) {
        BookDto bookToEdit = bookService.findById(id);
        model.addAttribute("bookToEdit", bookToEdit);
        return "edit_book_page";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute @Valid BookUpdateDto bookUpdateDto) {
        bookService.update(bookUpdateDto);
        return "redirect:/home/all";
    }
}
