package com.example.hw_6.repositories;

import com.example.hw_6.models.Author;
import com.example.hw_6.models.Book;
import com.example.hw_6.models.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@TestPropertySource("classpath:application-test.yml")
class JpaBookRepositoryTest {

    @Mock
    JpaBookRepository jpaBookRepository;

    Book book;
    Author author;
    Genre genre;

    @BeforeEach
    void setUp() {
        author = new Author(7L, "NEW_AUTHOR");
        genre = new Genre(7L, "NEW_NAME");
        book = new Book(7L, "new_title", author, genre);
    }

    @Test
    void findById() {
        assertEquals("BookTitle_1", jpaBookRepository.findById(1).orElse(new Book()).getTitle());
    }

    @Test
    void whenFindById_bookNotFound() {
        assertEquals(Optional.empty(), jpaBookRepository.findById(5));
    }

    @Test
    void findAll() {
        assertEquals(3, jpaBookRepository.findAll().size());
    }

    @Test
    void save() {
        jpaBookRepository.save(book);
        assertEquals(4, jpaBookRepository.findAll().size());
    }

    @Test
    void deleteById() {
        jpaBookRepository.deleteById(1);
        assertEquals(2, jpaBookRepository.findAll().size());
    }
}