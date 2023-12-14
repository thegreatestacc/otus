package com.example.hw_7.repositories;

import com.example.hw_7.models.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends ListCrudRepository<Book, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    Optional<Book> findById(long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    @Override
    List<Book> findAll();

    void deleteById(long id);
}
