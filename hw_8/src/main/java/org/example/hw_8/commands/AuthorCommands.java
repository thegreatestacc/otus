package org.example.hw_8.commands;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.converters.AuthorConverter;
import org.example.hw_8.services.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@ShellComponent
public class AuthorCommands {

    private final AuthorService service;
    private final AuthorConverter converter;

    @ShellMethod(key = "abid", value = "Find author by id")
    public String findAuthorById(String id) {
        return service.findById(id)
                .map(converter::authorToString)
                .orElse("Author with id: %s does not exist!".formatted(id));
    }

    @ShellMethod(key = "aa", value = "Find all authors")
    public String findAllAuthors() {
        return service.findAll().stream()
                .map(converter::authorToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }
}
