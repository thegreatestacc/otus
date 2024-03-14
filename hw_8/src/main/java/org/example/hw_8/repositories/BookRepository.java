package org.example.hw_8.repositories;

import org.example.hw_8.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
