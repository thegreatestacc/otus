package com.example.hw_6.repositories;

import com.example.hw_6.models.Genre;
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
class GenreRepositoryJdbcTest {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

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

    @Test
    void whenFindById_genreNotFound() {
        assertEquals(Optional.empty(), genreRepositoryJdbc.findById(5));
    }
}