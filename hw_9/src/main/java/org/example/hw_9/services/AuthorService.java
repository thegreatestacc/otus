package org.example.hw_9.services;

import org.example.hw_9.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
