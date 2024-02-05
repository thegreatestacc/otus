package org.example.hw_10.services;

import org.example.hw_10.dto.comment.CommentCreateDto;
import org.example.hw_10.dto.comment.CommentDto;
import org.example.hw_10.dto.comment.CommentUpdateDto;

import java.util.List;

public interface CommentService {

    CommentDto findById(long id);

    List<CommentDto> findAll();

    CommentDto create(CommentCreateDto commentCreateDto);

    CommentDto update(CommentUpdateDto commentUpdateDto);

    void deleteById(long id);
}
