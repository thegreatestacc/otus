package com.example.hw_6.commands;

import com.example.hw_6.converters.CommentConverter;
import com.example.hw_6.models.Comment;
import com.example.hw_6.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {
    private final CommentService commentService;

    private final CommentConverter converter;

    @ShellMethod(value = "Find all comments", key = "ac")
    public String findAllComments() {
        return commentService.findAll().stream()
                .map(converter::commentToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Find comment by id", key = "fcid")
    public String findCommentById(long id) {
        return commentService.findById(id)
                .map(converter::commentToString)
                .orElse("Comment with id %d not found").formatted(id);
    }

    @ShellMethod(value = "Update comment", key = "cupd")
    public String updateComment(Comment comment) {
        return commentService.update(comment).toString();
    }

    @ShellMethod(value = "Delete comment", key = "cdel")
    public void deleteComment(long id) {
        commentService.deleteById(id);
    }
}
