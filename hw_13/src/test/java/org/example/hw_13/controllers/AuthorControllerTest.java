package org.example.hw_13.controllers;

import org.example.hw_13.dto.author.AuthorDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Disabled
class AuthorControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    AuthorController authorController;

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void shouldReturnAuthor_whenGetAuthorByID(Long id) {
        AuthorDto actual = authorController.getAuthorById(id);
        assertEquals("Author_1", actual.getFullName());
    }
}