package com.example.hw_7.services;

import com.example.hw_7.models.Comment;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(long id);

    Iterable<Comment> findAll();

    Comment insert(Comment comment);

    Comment update(Comment comment);

    void deleteById(Long id);
}
