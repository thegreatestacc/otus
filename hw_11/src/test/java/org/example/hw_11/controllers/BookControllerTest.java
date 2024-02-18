package org.example.hw_11.controllers;

import org.example.hw_11.dto.book.BookCreateDto;
import org.example.hw_11.dto.book.BookDto;
import org.example.hw_11.dto.book.BookUpdateDto;
import org.example.hw_11.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class BookControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLR2DBCDatabaseContainer postgres = new PostgreSQLR2DBCDatabaseContainer(
            new PostgreSQLContainer<>("postgres:latest"));

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
        Flux<BookDto> bookDtoFlux = bookController.getAllBooks();
        Mono<List<BookDto>> list = bookDtoFlux.collectList();
        List<BookDto> actual = list.blockOptional().get();
        assertEquals(2, actual.size());
        assertEquals("BookTitle_2", actual.get(0).getTitle());
    }


    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldReturnBook_whenGetBookByID(Long id) {
        Mono<BookDto> actual = bookController.getBookById(id);
        assertEquals("BookTitle_1", actual.blockOptional().get().getTitle());
    }

    @Test
    void shouldUpdateBook_whenUpdateBook() {
        BookUpdateDto dto = new BookUpdateDto();
        dto.setBookId(1L);
        dto.setTitle("new_title");
        dto.setAuthorId(1L);
        dto.setGenreId(2L);

        BookDto updatedBook = bookController.updateBook(dto);

        assertEquals("new_title", updatedBook.getTitle());
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
        var all = bookController.getAllBooks().collectList().blockOptional().get();

        assertEquals(1, all.size());
        assertEquals("Title_10", actual.getTitle());
    }

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void shouldDeleteBook_whenDeleteByID(Long id) {
        bookController.deleteById(id);
        List<BookDto> actual = bookController.getAllBooks().collectList().blockOptional().get();

        assertEquals(2, actual.size());
    }

}
