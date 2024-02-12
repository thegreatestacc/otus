package org.example.hw_10.mappers;

import org.example.hw_10.dto.comment.CommentCreateDto;
import org.example.hw_10.dto.comment.CommentDto;
import org.example.hw_10.dto.comment.CommentUpdateDto;
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

    public Comment updateDtoToComment(CommentUpdateDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setComment(dto.getComment());
        return comment;
    }

    public CommentUpdateDto commentToCommentUpdateDto(Comment comment) {
        CommentUpdateDto dto = new CommentUpdateDto();
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        return dto;
    }

    public Comment createDtoToComment(CommentCreateDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setComment(dto.getComment());
        return comment;
    }

    public CommentCreateDto commentToCommentCreateDto(Comment comment) {
        CommentCreateDto dto = new CommentCreateDto();
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        return dto;
    }
}
