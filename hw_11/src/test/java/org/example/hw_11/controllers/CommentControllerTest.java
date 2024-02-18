package org.example.hw_11.controllers;

import org.example.hw_11.dto.comment.CommentDto;
import org.example.hw_11.dto.comment.CommentUpdateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLR2DBCDatabaseContainer postgres = new PostgreSQLR2DBCDatabaseContainer(
            new PostgreSQLContainer<>("postgres:latest"));

    static String allCommentsResult = "[{\"id\":2,\"comment\":\"comment_2\"},{\"id\":1,\"comment\":\"new_comment\"}]";

    static String commentByID = "{\"id\":1,\"comment\":\"comment_1\"}";

    @Autowired
    private CommentController commentController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllComments_whenGetAllComments() throws Exception {

        this.mockMvc.perform(get("/comment"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allCommentsResult)));
    }

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void shouldReturnComment_whenGetCommentByID(Long id) throws Exception {

        this.mockMvc.perform(get("/comment/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(commentByID)));
    }

    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldUpdateComment_whenUpdateCommentByID(Long id) {
        CommentUpdateDto dto = new CommentUpdateDto();
        dto.setId(id);
        dto.setComment("new_comment");

        CommentDto updatedComment = commentController.updateComment(dto);
        assertEquals("new_comment", updatedComment.getComment());
    }

    @ParameterizedTest
    @ValueSource(longs = 3L)
    void shouldDeleteComment_whenDeleteByID(Long id) throws Exception {
        this.mockMvc.perform(delete("/comment/" + id))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("")));
    }

}