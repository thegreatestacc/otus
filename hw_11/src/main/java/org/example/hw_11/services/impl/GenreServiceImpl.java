package org.example.hw_11.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.genre.GenreDto;
import org.example.hw_11.mappers.GenreMapper;
import org.example.hw_11.repositories.GenreRepository;
import org.example.hw_11.services.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Transactional(readOnly = true)
    @Override
    public Flux<GenreDto> findAll() {
        return genreRepository.findAll()
                .map(genreMapper::genreToDto)
                .switchIfEmpty(Flux.empty());
    }
}
