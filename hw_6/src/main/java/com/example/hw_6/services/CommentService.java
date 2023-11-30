package com.example.hw_6.services;

import com.example.hw_6.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(long id);

    List<Comment> findAll();

    Comment insert(Comment comment);

    Comment update(Comment comment);

    void deleteById(long id);
}
