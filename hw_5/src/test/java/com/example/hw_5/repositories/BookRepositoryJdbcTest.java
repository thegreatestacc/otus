package com.example.hw_5.repositories;

import com.example.hw_5.models.Author;
import com.example.hw_5.models.Book;
import com.example.hw_5.models.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@TestPropertySource("classpath:application-test.yml")
class BookRepositoryJdbcTest {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Mock
    BookRepositoryJdbc bookRepositoryJdbc;

    Book book;
    Author author;
    Genre genre;

    @BeforeEach
    void setUp() {
        bookRepositoryJdbc = new BookRepositoryJdbc(jdbcTemplate);
        author = new Author(7L, "NEW_AUTHOR");
        genre = new Genre(7L, "NEW_NAME");
        book = new Book(7L, "new_title", author, genre);
    }

    @Test
    void findById() {
        assertEquals("BookTitle_1", bookRepositoryJdbc.findById(1).orElse(new Book()).getTitle());
    }

    @Test
    void whenFindById_bookNotFound() {
        assertEquals(Optional.empty(), bookRepositoryJdbc.findById(5));
    }

    @Test
    void findAll() {
        assertEquals(3, bookRepositoryJdbc.findAll().size());
    }

    @Test
    void save() {
        bookRepositoryJdbc.save(book);
        assertEquals(4, bookRepositoryJdbc.findAll().size());
    }

    @Test
    void deleteById() {
        bookRepositoryJdbc.deleteById(1);
        assertEquals(2, bookRepositoryJdbc.findAll().size());
    }
}