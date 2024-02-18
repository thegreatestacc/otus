package org.example.hw_11.services;

import org.example.hw_11.dto.genre.GenreDto;
import reactor.core.publisher.Flux;

public interface GenreService {

    Flux<GenreDto> findAll();
}
