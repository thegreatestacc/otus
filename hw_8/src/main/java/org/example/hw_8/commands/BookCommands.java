package org.example.hw_8.commands;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.converters.BookConverter;
import org.example.hw_8.models.Book;
import org.example.hw_8.services.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class BookCommands {

    private final BookService service;
    private final BookConverter converter;

    @ShellMethod(key = "bbid", value = "Find book by id.")
    private String findById(String id) {
        return service.findById(id)
                .map(converter::bookToString)
                .orElse("Book with id %s does not exist!".formatted(id));
    }

    @ShellMethod(key = "ab", value = "Find all books.")
    public String findAllBooks() {
        return service.findAll().stream()
                .map(converter::bookToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(key = "ib", value = "Insert book.")
    public String insertBook(String title, String authorId, String genreId) {
        Book book = service.insert(title, authorId, genreId);
        return converter.bookToString(book);
    }

    @ShellMethod(key = "ub", value = "Update book.")
    public String updateBook(String id, String title, String authorId, String genreId) {
        Book book = service.update(id, title, authorId, genreId);
        return converter.bookToString(book);
    }

    @ShellMethod(key = "dbid", value = "Delete book by id.")
    public void deleteBook(String id) {
        service.delete(id);
    }
}
