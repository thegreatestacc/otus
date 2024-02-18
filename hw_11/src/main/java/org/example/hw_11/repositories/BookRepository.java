package org.example.hw_11.repositories;

import org.example.hw_11.models.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    Mono<Book> findById(long id);

    @Override
    Flux<Book> findAll();

    Mono<Void> deleteById(long id);
}
