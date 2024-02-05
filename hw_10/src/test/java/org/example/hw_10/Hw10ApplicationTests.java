package org.example.hw_10;

import org.example.hw_10.controllers.BookController;
import org.example.hw_10.dto.book.BookCreateDto;
import org.example.hw_10.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class Hw10ApplicationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void contextLoads() {
        assertThat(bookController).isNotNull();
    }

    @Test
    void shouldReturnOkStatus_whenGetAllBooksRequest() {
        var actual = bookController.getAllBooks();
        assertEquals(3, Objects.requireNonNull(actual.getBody()).size());
        assertEquals("BookTitle_1", actual.getBody().get(0).getTitle());
    }

    @Test
    void shouldReturnOkStatus_whenInsertBookRequest() {
        BookCreateDto dto = new BookCreateDto();
        dto.setBookId(4L);
        dto.setTitle("Title_10");
        dto.setAuthorId(1L);
        dto.setGenreId(2L);

        bookRepository.deleteAll();

        var actual = bookController.addBook(dto);
        var all = bookController.getAllBooks();

        assertEquals(1, Objects.requireNonNull(all.getBody()).size());
        assertEquals("Title_10", Objects.requireNonNull(actual.getBody()).getTitle());
    }

}
