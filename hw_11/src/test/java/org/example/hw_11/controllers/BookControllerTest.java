package org.example.hw_11.controllers;

import org.example.hw_11.dto.book.BookCreateDto;
import org.example.hw_11.dto.book.BookDto;
import org.example.hw_11.dto.book.BookUpdateDto;
import org.example.hw_11.models.Author;
import org.example.hw_11.models.Book;
import org.example.hw_11.models.Genre;
import org.example.hw_11.repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
class BookControllerTest {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.7");

    static {
        mongoDBContainer.start();
    }

    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(new Book(1L, "Book_Title_1", new Author(1L, "Author_Name_1"), new Genre(1L, "Genre_Name_1"))).blockOptional();
        repository.save(new Book(2L, "Book_Title_2", new Author(2L, "Author_Name_2"), new Genre(2L, "Genre_Name_2"))).blockOptional();
        repository.save(new Book(3L, "Book_Title_3", new Author(3L, "Author_Name_3"), new Genre(3L, "Genre_Name_3"))).blockOptional();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll().blockOptional();
    }

    @Test
    void shouldReturnOkStatus_whenGetAllBooksRequest() {
        List<BookDto> actual = bookController.getAllBooks().collectList().blockOptional().get();

        assertEquals(3, actual.size());
        assertEquals("Book_Title_1", actual.get(0).getTitle());
    }

    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldReturnBook_whenGetBookByID(Long id) {
        BookDto actual = bookController.getBookById(id).blockOptional().get();
        assertEquals("Book_Title_1", actual.getTitle());
    }

    @Test
    void shouldUpdateBook_whenUpdateBook() {
        BookUpdateDto dto = new BookUpdateDto();
        dto.setBookId(1L);
        dto.setTitle("new_title");
        dto.setAuthorId(1L);
        dto.setGenreId(2L);

        BookDto actual = bookController.updateBook(dto).blockOptional().get();

        assertEquals("new_title", actual.getTitle());
    }

    @Test
    void shouldReturnOkStatus_whenInsertBookRequest() {
        BookCreateDto dto = new BookCreateDto();
        dto.setBookId(4L);
        dto.setTitle("Title_10");
        dto.setAuthorId(1L);
        dto.setGenreId(2L);

        BookDto savedBook = bookController.addBook(dto).blockOptional().get();
        BookDto actual = bookController.getBookById(savedBook.getBookId()).blockOptional().get();

        var all = bookController.getAllBooks().collectList().blockOptional().get();

        assertEquals(4, all.size());
        assertEquals("Title_10", actual.getTitle());
    }

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void shouldDeleteBook_whenDeleteByID(Long id) {
        bookController.deleteById(id);
        List<BookDto> actual = bookController.getAllBooks().collectList().blockOptional().get();

        assertEquals(3, actual.size());
    }

}
