package com.example.hw_5.repositories;

import com.example.hw_5.models.Author;
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
class AuthorRepositoryJdbcTest {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

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

    @Test
    void whenFindById_authorNotFound() {
        assertEquals(Optional.empty(), authorRepositoryJdbc.findById(5));
    }
}