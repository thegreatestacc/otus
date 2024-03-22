package org.example.hw_13.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_13.dto.author.AuthorDto;
import org.example.hw_13.exceptions.NotFoundException;
import org.example.hw_13.mappers.AuthorMapper;
import org.example.hw_13.models.Author;
import org.example.hw_13.repositories.AuthorRepository;
import org.example.hw_13.services.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorDto findById(long id) {
        return authorRepository.findById(id)
                .map(authorMapper::authorToDto)
                .orElseThrow(() -> new NotFoundException("Can not find author with id %d !".formatted(id)));
    }
}
