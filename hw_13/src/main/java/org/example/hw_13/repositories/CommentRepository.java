package org.example.hw_13.repositories;

import org.example.hw_13.models.Comment;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CommentRepository extends ListCrudRepository<Comment, Long> {
    Optional<Comment> findById(long id);

    void deleteById(Long id);

}
