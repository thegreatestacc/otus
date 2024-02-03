package org.example.hw_8.services;

import org.example.hw_8.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(String id);

    List<Comment> findAll();

    Comment insert(Comment comment);

    Comment update(Comment comment);

    void delete(String id);
}
