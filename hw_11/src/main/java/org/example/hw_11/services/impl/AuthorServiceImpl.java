package org.example.hw_11.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.author.AuthorDto;
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

    @Transactional(readOnly = true)
    @Override
    public Flux<AuthorDto> findAll() {
        return authorRepository.findAll()
                .map(authorMapper::authorToDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<AuthorDto> findById(long id) {
        return authorRepository.findById(id)
                .map(authorMapper::authorToDto);
    }
}
