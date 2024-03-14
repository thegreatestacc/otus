package org.example.hw_11.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.comment.CommentCreateDto;
import org.example.hw_11.dto.comment.CommentDto;
import org.example.hw_11.dto.comment.CommentUpdateDto;
import org.example.hw_11.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public Flux<CommentDto> getAllComments() {
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CommentDto> getCommentById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @PostMapping("/create")
    public Mono<CommentDto> addComment(@RequestBody @Valid CommentCreateDto commentCreateDto) {
        return commentService.create(commentCreateDto);
    }

    @PatchMapping("/update")
    public Mono<CommentDto> updateComment(@RequestBody @Valid CommentUpdateDto commentUpdateDto) {
        return commentService.update(commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return commentService.deleteById(id);
    }
}
