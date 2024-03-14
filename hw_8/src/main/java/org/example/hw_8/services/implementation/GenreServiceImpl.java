package org.example.hw_8.services.implementation;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.models.Genre;
import org.example.hw_8.repositories.GenreRepository;
import org.example.hw_8.services.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }
}
