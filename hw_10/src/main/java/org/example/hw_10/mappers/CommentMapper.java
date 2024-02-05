package org.example.hw_10.mappers;

import org.example.hw_10.dto.comment.CommentDto;
import org.example.hw_10.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment dtoToComment(CommentDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setComment(dto.getComment());
        return comment;
    }

    public CommentDto commentToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        return dto;
    }
}
