package org.example.hw_9.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hw_9.dto.BookCreateDto;
import org.example.hw_9.dto.BookUpdateDto;
import org.example.hw_9.models.Book;
import org.example.hw_9.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("/all")
    public String showAllBooks(@RequestParam(required = false) String login, Model model) {
        List<Book> books = bookService.findAll();
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
        bookService.insert(bookCreateDto);
        return "redirect:/books/all";
    }

    @GetMapping("/editBook/{id}")
    public String getEditBookPage(Model model, @PathVariable Long id) {
        Book bookToEdit = bookService.findById(id);
        model.addAttribute("bookToEdit", bookToEdit);
        return "edit_book_page";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute @Valid BookUpdateDto bookUpdateDto) {
        bookService.update(bookUpdateDto);
        return "redirect:/books/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books/all";
    }
}
