package org.example.hw_10.repositories;

import org.example.hw_10.models.Genre;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface GenreRepository extends ListCrudRepository<Genre, Long> {

    Optional<Genre> findById(long id);
}