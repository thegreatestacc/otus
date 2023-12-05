package com.example.hw_7.repositories;

import com.example.hw_7.models.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findById(long id);

    List<Comment> findAll();

    Comment save(Comment comment);

    Comment update(Comment comment);

    void delete(long id);

}
