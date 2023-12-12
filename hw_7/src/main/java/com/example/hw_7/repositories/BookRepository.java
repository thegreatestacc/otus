package com.example.hw_7.repositories;

import com.example.hw_7.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findById(long id);

    void deleteById(long id);
}
