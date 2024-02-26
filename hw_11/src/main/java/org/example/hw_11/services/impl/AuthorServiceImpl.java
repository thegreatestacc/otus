package org.example.hw_11.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.author.AuthorDto;
import org.example.hw_11.exceptions.NotFoundException;
import org.example.hw_11.mappers.AuthorMapper;
import org.example.hw_11.repositories.AuthorRepository;
import org.example.hw_11.services.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    private final static String AUTHOR_NOT_FOUND_MESSAGE = "Author with id %d not found!";

    @Transactional(readOnly = true)
    @Override
    public Flux<AuthorDto> findAll() {
        return authorRepository.findAll()
                .map(authorMapper::authorToDto)
                .switchIfEmpty(Flux.empty());
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<AuthorDto> findById(long id) {
        return authorRepository.findById(id)
                .map(authorMapper::authorToDto)
                .switchIfEmpty(Mono.error(new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(id))));
    }
}
