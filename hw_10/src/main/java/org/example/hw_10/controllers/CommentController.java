package org.example.hw_10.controllers;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.dto.comment.CommentCreateDto;
import org.example.hw_10.dto.comment.CommentDto;
import org.example.hw_10.dto.comment.CommentUpdateDto;
import org.example.hw_10.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> result = commentService.findAll();
        if (result.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        CommentDto result = commentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentCreateDto commentCreateDto) {
        CommentDto result = commentService.create(commentCreateDto);
        if (result == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/update")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentUpdateDto commentUpdateDto) {
        CommentDto result = commentService.update(commentUpdateDto);
        if (result == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
