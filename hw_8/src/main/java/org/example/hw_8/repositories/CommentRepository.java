package org.example.hw_8.repositories;

import org.example.hw_8.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findCommentsByBookId(String id);
}
