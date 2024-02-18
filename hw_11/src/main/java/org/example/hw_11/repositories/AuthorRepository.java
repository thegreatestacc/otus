package org.example.hw_11.repositories;

import org.example.hw_11.models.Author;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {

    Mono<Author> findById(Long id);
}
