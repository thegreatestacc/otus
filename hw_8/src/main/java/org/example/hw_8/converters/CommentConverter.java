package org.example.hw_8.converters;

import org.example.hw_8.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    public String commentToString(Comment comment) {
        return "Id: %s, comment: %s".formatted(comment.getId(), comment.getComment());
    }
}
