package org.example.hw_11.repositories;

import org.example.hw_11.models.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CommentRepository extends ReactiveMongoRepository<Comment, Long> {
}
