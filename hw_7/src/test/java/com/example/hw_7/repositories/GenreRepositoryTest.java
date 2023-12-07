package com.example.hw_7.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource("classpath:application-test.yaml")
@Transactional(propagation = Propagation.NEVER)
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findAll() {
        var all = genreRepository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    void findById() {
        var genre = genreRepository.findById(1);
        var actualName = genre.get().getName();
        assertEquals(actualName, "Genre_1");
    }
}