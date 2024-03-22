package org.example.hw_13.services;

import org.example.hw_13.dto.author.AuthorDto;
import org.example.hw_13.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    AuthorDto findById(long id);
}
