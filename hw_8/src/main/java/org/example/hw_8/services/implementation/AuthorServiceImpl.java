package org.example.hw_8.services.implementation;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.models.Author;
import org.example.hw_8.repositories.AuthorRepository;
import org.example.hw_8.services.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> findById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }
}
