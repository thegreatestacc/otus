package org.example.hw_11.controllers;

import org.example.hw_11.dto.comment.CommentDto;
import org.example.hw_11.dto.comment.CommentUpdateDto;
import org.example.hw_11.models.Author;
import org.example.hw_11.models.Book;
import org.example.hw_11.models.Comment;
import org.example.hw_11.models.Genre;
import org.example.hw_11.repositories.BookRepository;
import org.example.hw_11.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class CommentControllerTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:6.0.7"));

    static {
        mongoDBContainer.start();
    }

    @Autowired
    private CommentController commentController;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        commentRepository.save(new Comment(1L, "Comment_1", new Book(1L, "title", new Author(), new Genre())))
                .blockOptional();

        bookRepository.save(new Book(1L, "title",
                        new Author(1L, "author_name"),
                        new Genre(1L, "genre_name")))
                .blockOptional();
    }

    @Test
    void shouldReturnAllComments_whenGetAllComments() {
        List<CommentDto> actual = commentController
                .getAllComments()
                .collectList()
                .blockOptional()
                .get();

        assertEquals(1, actual.size());
    }

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void shouldReturnComment_whenGetCommentByID(Long id) {
        CommentDto actual = commentController.getCommentById(id)
                .blockOptional().get();

        assertEquals("Comment_1", actual.getComment());
    }

    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldUpdateComment_whenUpdateCommentByID(Long id) {
        CommentUpdateDto dto = new CommentUpdateDto();
        dto.setId(id);
        dto.setComment("new_comment");
        dto.setBookId(1L);

        CommentDto actual = commentController
                .updateComment(dto)
                .blockOptional().get();

        assertEquals("new_comment", actual.getComment());
    }

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void shouldDeleteComment_whenDeleteByID(Long id) {
        commentController.deleteById(id);
        List<CommentDto> actual = commentController.getAllComments()
                .collectList()
                .blockOptional()
                .get();
        assertEquals(1, actual.size());
    }

}