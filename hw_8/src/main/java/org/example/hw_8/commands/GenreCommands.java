package org.example.hw_8.commands;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.converters.GenreConverter;
import org.example.hw_8.repositories.GenreRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class GenreCommands {

    private final GenreRepository repository;
    private final GenreConverter converter;

    @ShellMethod(key = "fage", value = "Find all genres.")
    public String findAllGenres() {
        return repository.findAll().stream()
                .map(converter::genreToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }
}
