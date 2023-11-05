package com.example.hw_5.repositories;

import com.example.hw_5.models.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class GenreRepositoryJdbcTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Mock
    GenreRepositoryJdbc genreRepositoryJdbc;

    @BeforeEach
    void setUp() {
        genreRepositoryJdbc = new GenreRepositoryJdbc(jdbcTemplate);
    }

    @Test
    void findAll() {
        assertEquals(3, genreRepositoryJdbc.findAll().size());
    }

    @Test
    void findById() {
        assertEquals("Genre_1", genreRepositoryJdbc.findById(1).orElse(new Genre()).getName());
    }
}