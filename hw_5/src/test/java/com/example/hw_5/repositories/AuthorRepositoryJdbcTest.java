package com.example.hw_5.repositories;

import com.example.hw_5.models.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class AuthorRepositoryJdbcTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Mock
    AuthorRepositoryJdbc authorRepositoryJdbc;

    @BeforeEach
    void setUp() {
        authorRepositoryJdbc = new AuthorRepositoryJdbc(jdbcTemplate);
    }

    @Test
    void findAll() {
        assertEquals(3, authorRepositoryJdbc.findAll().size());
    }

    @Test
    void findById() {
        assertEquals("Author_1",
                authorRepositoryJdbc.findById(1).orElse(new Author()).getFullName());
    }

}