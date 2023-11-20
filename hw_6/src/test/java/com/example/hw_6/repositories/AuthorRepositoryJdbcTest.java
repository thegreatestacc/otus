package com.example.hw_6.repositories;

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
class AuthorRepositoryJdbcTest {
    @Mock
    AuthorRepositoryJdbc authorRepositoryJdbc;

    @Test
    void findAll() {
        assertEquals(3, authorRepositoryJdbc.findAll().size());
    }

    @Test
    void findById() {
        var optional = authorRepositoryJdbc.findById(1);
        var expectedName = optional.get().getFullName();
        assertEquals(expectedName, "Author_1");

    }

    @Test
    void whenFindById_authorNotFound() {
        assertEquals(Optional.empty(), authorRepositoryJdbc.findById(5));
    }
}