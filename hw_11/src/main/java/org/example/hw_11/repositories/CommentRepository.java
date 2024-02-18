package org.example.hw_11.repositories;

import org.example.hw_11.models.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CommentRepository extends ReactiveCrudRepository<Comment, Long> {
    Mono<Comment> findById(long id);

    Mono<Void> deleteById(Long id);

}
