package com.example.hw_6.repositories;

import com.example.hw_6.models.Genre;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
@Import(AuthorRepositoryJdbc.class)
@Transactional(propagation = Propagation.NEVER)
class GenreRepositoryJdbcTest {
    @Mock
    GenreRepositoryJdbc genreRepositoryJdbc;

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