package com.example.hw_7.services;

import com.example.hw_7.models.Author;

import java.util.Optional;

public interface AuthorService {
    Iterable<Author> findAll();

    Optional<Author> findById(Long id);
}
