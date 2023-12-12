package com.example.hw_7.repositories;

import com.example.hw_7.models.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findById(long id);
}
