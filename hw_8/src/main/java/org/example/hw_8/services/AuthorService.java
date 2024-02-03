package org.example.hw_8.services;

import org.example.hw_8.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(String id);

    List<Author> findAll();
}
