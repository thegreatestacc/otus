package org.example.hw_13.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.hw_13.dto.book.BookDto;
import org.example.hw_13.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookControllerTest.class)
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    private final String BASE_URL = "http://localhost:8089";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private List<BookDto> books;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        books = List.of(new BookDto(1L, "Title_1", 1L, 1L));
        books = List.of(new BookDto(2L, "Title_2", 2L, 2L));
        books = List.of(new BookDto(3L, "Title_3", 3L, 3L));
    }

    @Test
    void shouldReturnOkStatus_whenGetAllBooksRequest() throws Exception {
        when(bookService.findAll()).thenReturn(books);
        mockMvc
                .perform(get(this.BASE_URL + "/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBook_whenGetBookByID() throws Exception {
        mockMvc
                .perform(get(this.BASE_URL + "/2")
                        .with(httpBasic("harry", "potter"))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Disabled
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
                                .content(mapper.writeValueAsString(new BookDto(2L, "new_title", 2L, 3L)))
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
