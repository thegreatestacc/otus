package org.example.hw_9.controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class BookControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @LocalServerPort
    private Integer serverPort;

    @Autowired
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:8089:" + serverPort + "/books/all";
    }

    @Test
    void contextLoad() {
        assertThat(bookController).isNotNull();
    }

    @Test
    void shouldReturnOkStatus_whenGetAllBooksRequest() throws Exception {
        this.mockMvc.perform(get("/books/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkStatus_whenCreateBook() throws Exception {
        this.mockMvc.perform(get("/books/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldReturnOkStatus_whenDeleteBook() throws Exception {
        this.mockMvc.perform(get("/books/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}