package org.example.hw_10.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.models.Author;
import org.example.hw_10.repositories.AuthorRepository;
import org.example.hw_10.services.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(long id) {
        return authorRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
