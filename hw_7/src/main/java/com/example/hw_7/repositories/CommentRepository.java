package com.example.hw_7.repositories;

import com.example.hw_7.models.Comment;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CommentRepository extends ListCrudRepository<Comment, Long> {
    Optional<Comment> findById(long id);

    void deleteById(Long id);

}
