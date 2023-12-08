package org.example.hw_8.repositories;

import org.example.hw_8.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book insert(String title, String authorId, String genreId);

    Book update(String id, String title, String authorId, String genreId);

    void deleteById(String id);
}
