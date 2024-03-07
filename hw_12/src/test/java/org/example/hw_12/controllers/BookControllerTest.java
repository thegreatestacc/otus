package org.example.hw_12.controllers;

import org.example.hw_12.dto.book.BookDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    private final String BASE_URL = "http://localhost:8089";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
        assertThat(bookController).isNotNull();
    }

    @Test
    void shouldReturnOkStatus_whenGetAllBooksRequest() throws Exception {
        mockMvc
                .perform(get(this.BASE_URL + "/home/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attributeExists("books"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRedirectToLoginPage_whenUserNotAuthorized() throws Exception {
        mockMvc
                .perform(get(this.BASE_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("http://localhost:8089/login"));
    }

    @Test
    @WithMockUser(username = "harry", password = "potter")
    void shouldReturnBook_whenGetBookByID() throws Exception {
        mockMvc
                .perform(get(this.BASE_URL + "/1")
                        .with(httpBasic("harry", "potter"))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkStatus_whenInsertBookRequest() throws Exception {
        mockMvc
                .perform(
                        post(this.BASE_URL + "/create")
                                .with(httpBasic("harry", "potter"))
                                .content(mapper.writeValueAsString(new BookDto(1L, "new_title", 2L, 3L)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateBook_whenUpdateBook() throws Exception {
        mockMvc
                .perform(
                        patch(this.BASE_URL + "/update")
                                .with(httpBasic("harry", "potter"))
                                .content(mapper.writeValueAsString(new BookDto(1L, "new_title", 2L, 3L)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteBook_whenDeleteByID() throws Exception {
        mockMvc
                .perform(
                        delete(this.BASE_URL + "/1")
                                .with(httpBasic("harry", "potter"))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }
}
