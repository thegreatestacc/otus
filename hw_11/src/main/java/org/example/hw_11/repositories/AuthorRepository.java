package org.example.hw_11.repositories;

import org.example.hw_11.models.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AuthorRepository extends ReactiveMongoRepository<Author, Long> {

    Mono<Author> findById(Long id);
}
