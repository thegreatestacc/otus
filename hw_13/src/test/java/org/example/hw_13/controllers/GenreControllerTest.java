package org.example.hw_13.controllers;

import org.example.hw_13.dto.genre.GenreDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class GenreControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private GenreController genreController;

    @Test
    void shouldReturnAllGenres_whenGetAllGenres() {
        List<GenreDto> actual = genreController.getAllGenres();
        assertEquals(3, actual.size());
    }
}