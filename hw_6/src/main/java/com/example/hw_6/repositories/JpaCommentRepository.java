package com.example.hw_6.repositories;

import com.example.hw_6.models.Comment;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaCommentRepository implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }
}
