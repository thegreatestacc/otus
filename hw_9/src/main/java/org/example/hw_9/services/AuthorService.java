package org.example.hw_9.services;

import org.example.hw_9.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author findById(long id);
}
