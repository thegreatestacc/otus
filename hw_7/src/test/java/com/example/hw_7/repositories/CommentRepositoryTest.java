package com.example.hw_7.repositories;

import com.example.hw_7.models.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource("classpath:application-test.yaml")
@Transactional(propagation = Propagation.NEVER)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findById() {
        var optionalComment = commentRepository.findById(1);
        var expectedComment = optionalComment.get().getComment();
        assertEquals(expectedComment, "omment_1");
    }

    @Test
    void findAll() {
        var all = commentRepository.findAll();
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void save() {
        var savedComment = commentRepository.save(new Comment());
        assertThat(savedComment.getId()).isGreaterThan(0);
    }

    @Test
    void update() {
        var optionalComment = commentRepository.findById(1);
        var comment = optionalComment.get();
        comment.setComment("another_comment");
        commentRepository.save(comment);
        assertEquals("another_comment", comment.getComment());
    }

    @Test
    void delete() {
        commentRepository.deleteById(1L);
        Comment comment = null;
        var optionalComment = commentRepository.findById(1);
        if (optionalComment.isPresent()) {
            comment = optionalComment.get();
        }
        Assertions.assertThat(comment).isNull();
    }
}