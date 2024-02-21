package org.example.hw_11.controllers;

import org.example.hw_11.dto.genre.GenreDto;
import org.example.hw_11.models.Genre;
import org.example.hw_11.repositories.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class GenreControllerTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:6.0.7"));

    @Autowired
    private GenreController genreController;

    @BeforeEach
    void setUp(@Autowired GenreRepository repository) {
        repository.save(new Genre(1L, "Genre_Name_1")).blockOptional();
        repository.save(new Genre(2L, "Genre_Name_2")).blockOptional();
        repository.save(new Genre(3L, "Genre_Name_3")).blockOptional();
    }

    @Test
    void shouldReturnAllGenres_whenGetAllGenres() {
        List<GenreDto> actual = genreController.getAllGenres().collectList().blockOptional().get();
        assertEquals(3, actual.size());
    }
}