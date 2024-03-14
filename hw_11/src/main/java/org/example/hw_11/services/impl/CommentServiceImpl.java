package org.example.hw_11.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.comment.CommentCreateDto;
import org.example.hw_11.dto.comment.CommentDto;
import org.example.hw_11.dto.comment.CommentUpdateDto;
import org.example.hw_11.exceptions.NotFoundException;
import org.example.hw_11.mappers.CommentMapper;
import org.example.hw_11.models.Comment;
import org.example.hw_11.repositories.BookRepository;
import org.example.hw_11.repositories.CommentRepository;
import org.example.hw_11.services.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    private final CommentMapper commentMapper;

    private static final String COMMENT_NOT_FOND_MESSAGE = "Comment with id %d not found!";
    private final static String BOOK_NOT_FOUND_MESSAGE = "Book with id %d not found!";


    @Transactional(readOnly = true)
    @Override
    public Mono<CommentDto> findById(long id) {
        return commentRepository.findById(id)
                .map(commentMapper::commentToDto)
                .switchIfEmpty(Mono.error(new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(id))));
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<CommentDto> findAll() {
        return commentRepository.findAll()
                .map(commentMapper::commentToDto)
                .switchIfEmpty(Flux.empty());
    }

    @Transactional
    @Override
    public Mono<CommentDto> create(CommentCreateDto commentCreateDto) {
        return bookRepository.findById(commentCreateDto.getBookId())
                .switchIfEmpty(Mono.error(new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(commentCreateDto.getBookId()))))
                .flatMap(book -> commentRepository.save(
                        new Comment(commentCreateDto.getId(), commentCreateDto.getComment(), book)
                )).map(commentMapper::commentToDto);
    }

    @Transactional
    @Override
    public Mono<CommentDto> update(CommentUpdateDto commentUpdateDto) {
        return commentRepository.findById(commentUpdateDto.getId())
                .switchIfEmpty(Mono.error(new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(commentUpdateDto.getId()))))
                .zipWith(bookRepository.findById(commentUpdateDto.getBookId()))
                .switchIfEmpty(Mono.error(new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(commentUpdateDto.getBookId()))))
                .flatMap(tuple -> {
                    tuple.getT1().setComment(commentUpdateDto.getComment());
                    tuple.getT1().setBook(tuple.getT2());
                    return commentRepository.save(tuple.getT1());
                }).map(commentMapper::commentToDto);
    }

    @Transactional
    @Override
    public Mono<Void> deleteById(long id) {
        return commentRepository.deleteById(id);
    }

}
