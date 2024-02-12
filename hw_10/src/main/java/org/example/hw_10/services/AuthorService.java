package org.example.hw_10.services;

import org.example.hw_10.dto.author.AuthorDto;
import org.example.hw_10.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    AuthorDto findById(long id);
}
