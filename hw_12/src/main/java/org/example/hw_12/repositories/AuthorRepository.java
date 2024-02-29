package org.example.hw_12.repositories;

import org.example.hw_12.models.Author;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {

    Optional<Author> findById(Long id);
}
