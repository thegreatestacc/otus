package org.example.hw_11.controllers;

import org.example.hw_11.dto.author.AuthorDto;
import org.example.hw_11.models.Author;
import org.example.hw_11.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthorControllerTest {

    @Autowired
    AuthorController authorController;

    @BeforeEach
    void setUp(@Autowired AuthorRepository repository) {
        repository.saveAll(List.of(
                new Author(1L, "Author_Test_Name1"),
                new Author(2L, "Author_Test_Name2"),
                new Author(3L, "Author_Test_Name3"))
        ).blockLast();
    }

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void shouldReturnAuthor_whenGetAuthorByID(Long id) {
        AuthorDto actual = authorController.getAuthorById(id).blockOptional().get();
        assertEquals("Author_Test_Name1", actual.getFullName());
    }
}