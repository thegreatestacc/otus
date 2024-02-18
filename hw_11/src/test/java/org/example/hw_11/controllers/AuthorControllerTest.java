package org.example.hw_11.controllers;

import org.example.hw_11.dto.author.AuthorDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AuthorControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLR2DBCDatabaseContainer postgres = new PostgreSQLR2DBCDatabaseContainer(
            new PostgreSQLContainer<>("postgres:latest"));

    @Autowired
    AuthorController authorController;

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void shouldReturnAuthor_whenGetAuthorByID(Long id) {
        Mono<AuthorDto> actual = authorController.getAuthorById(id);
        assertEquals("Author_1", actual.toString());
    }
}