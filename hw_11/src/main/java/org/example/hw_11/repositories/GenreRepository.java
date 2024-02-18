package org.example.hw_11.repositories;

import org.example.hw_11.models.Genre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {

    Mono<Genre> findById(long id);
}