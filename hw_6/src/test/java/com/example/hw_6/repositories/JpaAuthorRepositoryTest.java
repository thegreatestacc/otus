package com.example.hw_6.repositories;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
@Import(JpaAuthorRepository.class)
@Transactional(propagation = Propagation.NEVER)
class JpaAuthorRepositoryTest {
    @Mock
    JpaAuthorRepository jpaAuthorRepository;

    @Test
    void findAll() {
        assertEquals(3, jpaAuthorRepository.findAll().size());
    }

    @Test
    void findById() {
        var optional = jpaAuthorRepository.findById(1);
        var expectedName = optional.get().getFullName();
        assertEquals(expectedName, "Author_1");

    }

    @Test
    void whenFindById_authorNotFound() {
        assertEquals(Optional.empty(), jpaAuthorRepository.findById(5));
    }
}