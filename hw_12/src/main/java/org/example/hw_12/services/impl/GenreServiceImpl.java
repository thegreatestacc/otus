package org.example.hw_12.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_12.dto.genre.GenreDto;
import org.example.hw_12.mappers.GenreMapper;
import org.example.hw_12.repositories.GenreRepository;
import org.example.hw_12.services.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Transactional(readOnly = true)
    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream()
                .map(genreMapper::genreToDto)
                .collect(Collectors.toList());
    }
}
