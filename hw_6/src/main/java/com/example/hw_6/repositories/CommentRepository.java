package com.example.hw_6.repositories;

import com.example.hw_6.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);

    List<Comment> findAll();

    Comment save(Comment comment);

    Comment update(Comment comment);

    void delete(long id);

}
