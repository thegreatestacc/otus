package com.example.hw_7.repositories;

import com.example.hw_7.models.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource("classpath:application-test.yaml")
@Transactional(propagation = Propagation.NEVER)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findById() {
        var bookOptional = bookRepository.findById(1);
        var title = bookOptional.get().getTitle();
        assertEquals("BookTitle_1", title);
    }

    @Test
    void findAll() {
        var all = bookRepository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    void save() {
        var savedBook = bookRepository.save(new Book());
        assertThat(savedBook.getId()).isGreaterThan(0);
    }

    @Test
    void deleteById() {
        bookRepository.deleteById(1);
        Book book = null;
        var optionalBook = bookRepository.findById(1);
        if (optionalBook.isPresent()) {
            book = optionalBook.get();
        }
        Assertions.assertThat(book).isNull();
    }
}