package org.example.hw_12.services;

import org.example.hw_12.dto.author.AuthorDto;
import org.example.hw_12.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    AuthorDto findById(long id);
}
