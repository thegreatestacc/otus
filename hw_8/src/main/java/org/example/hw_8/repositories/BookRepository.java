package org.example.hw_8.repositories;

import org.example.hw_8.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findById(String id);

    List<Book> findAll();

    void deleteById(String id);
}
