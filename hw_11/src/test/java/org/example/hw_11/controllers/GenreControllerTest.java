package org.example.hw_11.controllers;

import org.example.hw_11.dto.genre.GenreDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class GenreControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLR2DBCDatabaseContainer postgres = new PostgreSQLR2DBCDatabaseContainer(
            new PostgreSQLContainer<>("postgres:latest"));

    @Autowired
    private GenreController genreController;

    @Test
    void shouldReturnAllGenres_whenGetAllGenres() {
        List<GenreDto> actual = genreController.getAllGenres().collectList().blockOptional().get();
        assertEquals(3, actual.size());
    }
}