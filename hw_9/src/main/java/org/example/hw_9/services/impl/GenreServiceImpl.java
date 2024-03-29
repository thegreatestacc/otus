package org.example.hw_9.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_9.models.Genre;
import org.example.hw_9.repositories.GenreRepository;
import org.example.hw_9.services.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
