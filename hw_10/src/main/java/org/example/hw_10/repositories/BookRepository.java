package org.example.hw_10.repositories;

import org.example.hw_10.models.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends ListCrudRepository<Book, Long> {
    @EntityGraph(value = "book-entity-graph")
    Optional<Book> findById(long id);

    @EntityGraph(value = "book-entity-graph")
    @Override
    List<Book> findAll();

    void deleteById(long id);
}
