package org.example.hw_11.services;

import org.example.hw_11.dto.author.AuthorDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {
    Flux<AuthorDto> findAll();

    Mono<AuthorDto> findById(long id);
}
