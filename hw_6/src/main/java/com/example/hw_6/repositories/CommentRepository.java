package com.example.hw_6.repositories;

import com.example.hw_6.models.Comment;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);

}
