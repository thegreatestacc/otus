package org.example.hw_11.services;

import org.example.hw_11.dto.comment.CommentCreateDto;
import org.example.hw_11.dto.comment.CommentDto;
import org.example.hw_11.dto.comment.CommentUpdateDto;
import reactor.core.publisher.Flux;

public interface CommentService {

    CommentDto findById(long id);

    Flux<CommentDto> findAll();

    CommentDto create(CommentCreateDto commentCreateDto);

    CommentDto update(CommentUpdateDto commentUpdateDto);

    void deleteById(long id);
}
