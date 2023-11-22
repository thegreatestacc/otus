package com.example.hw_6.repositories;

import com.example.hw_6.models.Author;
import com.example.hw_6.models.Book;
import com.example.hw_6.models.Comment;
import com.example.hw_6.models.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
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
@Import(JpaBookRepository.class)
@Transactional(propagation = Propagation.NEVER)
class JpaBookRepositoryTest {

    @Autowired
    JpaBookRepository jpaBookRepository;

    @Autowired
    TestEntityManager entityManager;

    Book book;
    Author author;
    Genre genre;
    Comment comment;

    @BeforeEach
    void setUp() {
        author = new Author(7L, "NEW_AUTHOR");
        genre = new Genre(7L, "NEW_NAME");
        comment = new Comment(7L, "test_comment");
        book = new Book(7L, "new_title", author, genre, comment);
    }

    @Test
    void findById() {
        assertEquals("BookTitle_1", jpaBookRepository.findById(1).orElse(new Book()).getTitle());
    }

    @Test
    void whenFindById_bookNotFound() {
        assertEquals(Optional.empty(), jpaBookRepository.findById(5));
    }

    @Test
    void findAll() {
        assertEquals(3, jpaBookRepository.findAll().size());
    }

    @Test
    void save() {
        jpaBookRepository.save(book);
        assertEquals(4, jpaBookRepository.findAll().size());
    }

    @Test
    void deleteById() {
        jpaBookRepository.deleteById(1);
        assertEquals(2, jpaBookRepository.findAll().size());
    }

    @Test
    void findCommentById() {

    }
}