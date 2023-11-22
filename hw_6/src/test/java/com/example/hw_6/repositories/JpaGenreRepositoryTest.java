package com.example.hw_6.repositories;

import com.example.hw_6.models.Genre;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
@Import(JpaAuthorRepository.class)
@Transactional(propagation = Propagation.NEVER)
class JpaGenreRepositoryTest {
    @Autowired
    JpaGenreRepository jpaGenreRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void findAll() {
        assertEquals(3, jpaGenreRepository.findAll().size());
    }

    @Test
    void findById() {
        assertEquals("Genre_1", jpaGenreRepository.findById(1).orElse(new Genre()).getName());
    }

    @Test
    void whenFindById_genreNotFound() {
        assertEquals(Optional.empty(), jpaGenreRepository.findById(5));
    }
}