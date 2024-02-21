package org.example.hw_11.services;

import org.example.hw_11.dto.comment.CommentCreateDto;
import org.example.hw_11.dto.comment.CommentDto;
import org.example.hw_11.dto.comment.CommentUpdateDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {

    Mono<CommentDto> findById(long id);

    Flux<CommentDto> findAll();

    Mono<CommentDto> create(CommentCreateDto commentCreateDto);

    Mono<CommentDto> update(CommentUpdateDto commentUpdateDto);

    void deleteById(long id);
}
