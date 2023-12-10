package org.example.hw_8.commands;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.converters.CommentConverter;
import org.example.hw_8.models.Comment;
import org.example.hw_8.services.CommentService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService service;
    private final CommentConverter converter;

    @ShellMethod(key = "fcid", value = "Find comment by id.")
    public String findById(String id) {
        return service.findById(id)
                .map(converter::commentToString)
                .orElse("Comment with id %s does not exist.".formatted(id));
    }

    @ShellMethod(key = "fac", value = "Find all comments.")
    public String findAll() {
        return service.findAll().stream()
                .map(converter::commentToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(key = "insc", value = "Insert comment.")
    public String insert(Comment comment) {
        return service.insert(comment).toString();
    }

    @ShellMethod(key = "upcm", value = "Update comment.")
    public String update(Comment comment) {
        return service.update(comment).toString();
    }

    @ShellMethod(key = "dcom", value = "Delete comment.")
    public void delete(String id) {
        service.delete(id);
    }
}
