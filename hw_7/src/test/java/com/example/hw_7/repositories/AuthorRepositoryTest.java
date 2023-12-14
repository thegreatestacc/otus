package com.example.hw_7.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource("classpath:application-test.yaml")
@Transactional(propagation = Propagation.NEVER)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAll() {
        var all = authorRepository.findAll();
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void findById() {
        var author = authorRepository.findById(1L);
        String expectedName = author.get().getFullName();
        assertEquals(expectedName, "Author_1");
    }

}
