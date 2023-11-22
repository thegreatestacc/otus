package com.example.hw_6.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
@Import(JpaCommentRepository.class)
@Transactional(propagation = Propagation.NEVER)
class JpaCommentRepositoryTest {

    @Autowired
    JpaCommentRepository jpaCommentRepository;

    @Autowired
    TestEntityManager entityManager;
    @Test
    void findById() {
    }
}