package com.example.hw_7.repositories;

import com.example.hw_7.models.Genre;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface GenreRepository extends ListCrudRepository<Genre, Long> {

    Optional<Genre> findById(long id);
}
