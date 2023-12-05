package com.example.hw_7.converts;

import com.example.hw_7.models.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    public String commentToString(Comment comment) {
        return "Id: %d, comment: %s".formatted(comment.getId(), comment.getComment());
    }
}
