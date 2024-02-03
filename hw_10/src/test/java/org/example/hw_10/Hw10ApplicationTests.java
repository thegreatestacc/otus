package org.example.hw_10;

import org.example.hw_10.controllers.BookController;
import org.example.hw_10.dto.BookCreateDto;
import org.example.hw_10.models.Book;
import org.example.hw_10.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

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
        List<Book> actual = bookController.getAllBooks();
        assertEquals(3, actual.size());
        assertEquals("BookTitle_1", actual.get(0).getTitle());
    }

    @Test
    void shouldReturnOkStatus_whenInsertBookRequest() {
        BookCreateDto dto = new BookCreateDto();
        dto.setBookId(4L);
        dto.setTitle("Title_10");
        dto.setAuthorId(1L);
        dto.setGenreId(2L);

        bookRepository.deleteAll();

        Book actual = bookController.addBook(dto);
        List<Book> all = bookController.getAllBooks();

        assertEquals(1, all.size());
        assertEquals("Title_10", actual.getTitle());
    }

}
