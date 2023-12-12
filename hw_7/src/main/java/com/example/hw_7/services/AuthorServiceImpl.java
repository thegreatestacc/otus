package com.example.hw_7.services;

import com.example.hw_7.models.Author;
import com.example.hw_7.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
}
