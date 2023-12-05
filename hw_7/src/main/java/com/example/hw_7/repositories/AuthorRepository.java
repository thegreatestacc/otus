package com.example.hw_7.repositories;

import com.example.hw_7.models.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
