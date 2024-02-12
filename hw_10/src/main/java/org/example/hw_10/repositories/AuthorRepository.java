package org.example.hw_10.repositories;

import org.example.hw_10.models.Author;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {

    Optional<Author> findById(Long id);
}
