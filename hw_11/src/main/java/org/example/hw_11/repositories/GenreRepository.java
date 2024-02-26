package org.example.hw_11.repositories;

import org.example.hw_11.models.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GenreRepository extends ReactiveMongoRepository<Genre, Long> {
}