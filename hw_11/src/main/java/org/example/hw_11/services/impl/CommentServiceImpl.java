package org.example.hw_11.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.comment.CommentCreateDto;
import org.example.hw_11.dto.comment.CommentDto;
import org.example.hw_11.dto.comment.CommentUpdateDto;
import org.example.hw_11.exceptions.NotFoundException;
import org.example.hw_11.mappers.CommentMapper;
import org.example.hw_11.models.Comment;
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
    
    private final CommentMapper commentMapper;

    private static final String COMMENT_NOT_FOND_MESSAGE = "Comment with id %d not found!";

    @Transactional(readOnly = true)
    @Override
    public CommentDto findById(long id) {
        return commentRepository.findById(id)
                .map(commentMapper::commentToDto)
                .blockOptional()
                .orElseThrow(() -> new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(id)));
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<CommentDto> findAll() {
        return commentRepository.findAll()
                .map(commentMapper::commentToDto);
    }

    @Transactional
    @Override
    public CommentDto create(CommentCreateDto commentCreateDto) {
        Comment savedComment = saveCreate(commentCreateDto)
                .blockOptional()
                .orElseThrow(() -> new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(commentCreateDto.getId())));
        return commentMapper.commentToDto(savedComment);
    }

    @Transactional
    @Override
    public CommentDto update(CommentUpdateDto commentUpdateDto) {
        if (commentRepository.findById(commentUpdateDto.getId()).blockOptional().isEmpty())
            throw new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(commentUpdateDto.getId()));
        Comment savedComment = saveUpdate(commentUpdateDto)
                .blockOptional()
                .orElseThrow(() -> new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(commentUpdateDto.getId())));
        return commentMapper.commentToDto(savedComment);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    protected Mono<Comment> saveUpdate(CommentUpdateDto dto) {
        return commentRepository.save(commentMapper.updateDtoToComment(dto));
    }

    protected Mono<Comment> saveCreate(CommentCreateDto dto) {
        return commentRepository.save(commentMapper.createDtoToComment(dto));
    }
}
