package com.example.hw_7.repositories;

import com.example.hw_7.models.Author;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {

    Optional<Author> findById(Long id);
}
