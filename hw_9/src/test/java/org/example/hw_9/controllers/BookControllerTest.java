package org.example.hw_9.controllers;

import org.example.hw_9.models.Author;
import org.example.hw_9.models.Book;
import org.example.hw_9.models.Genre;
import org.example.hw_9.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {


    @Autowired
    private BookController bookController;

    @Autowired
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

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