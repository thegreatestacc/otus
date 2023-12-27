package org.example.hw_9.repositories;

import org.example.hw_9.models.Author;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {

    Optional<Author> findById(Long id);
}
