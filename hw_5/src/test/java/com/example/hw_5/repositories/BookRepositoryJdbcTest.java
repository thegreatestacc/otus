package com.example.hw_5.repositories;

import com.example.hw_5.models.Author;
import com.example.hw_5.models.Book;
import com.example.hw_5.models.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class BookRepositoryJdbcTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Mock
    BookRepositoryJdbc bookRepositoryJdbc;

    @Mock
    Book book;

    @Mock
    Author author;

    @Mock
    Genre genre;

    @BeforeEach
    void setUp() {
        bookRepositoryJdbc = new BookRepositoryJdbc(jdbcTemplate);
        author = new Author(7, "NEW_AUTHOR");
        genre = new Genre(7, "NEW_NAME");
        book = new Book(7, "new_title", author, genre);
    }

    @Test
    void findById() {
        assertEquals("BookTitle_1", bookRepositoryJdbc.findById(1).orElse(new Book()).getTitle());
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