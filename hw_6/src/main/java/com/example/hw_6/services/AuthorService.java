package com.example.hw_6.services;

import com.example.hw_6.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
