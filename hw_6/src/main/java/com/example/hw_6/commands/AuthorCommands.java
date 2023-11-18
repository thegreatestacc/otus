package com.example.hw_6.commands;

import com.example.hw_6.converters.AuthorConverter;
import com.example.hw_6.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    private final AuthorConverter authorConverter;

    @ShellMethod(value = "Find all authors", key = "aa")
    public String findAllAuthors() {
        return authorService.findAll().stream()
                .map(authorConverter::authorToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(key = "abid", value = "Find author by id")
    public String findAuthorById(long id) {
        return authorService.findById(id)
                .map(authorConverter::authorToString)
                .orElse("Author with id %d not found".formatted(id));
    }
}
