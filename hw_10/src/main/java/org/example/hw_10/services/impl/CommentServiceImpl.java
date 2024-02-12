package org.example.hw_10.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.dto.comment.CommentCreateDto;
import org.example.hw_10.dto.comment.CommentDto;
import org.example.hw_10.dto.comment.CommentUpdateDto;
import org.example.hw_10.exceptions.NotFoundException;
import org.example.hw_10.mappers.CommentMapper;
import org.example.hw_10.models.Comment;
import org.example.hw_10.repositories.CommentRepository;
import org.example.hw_10.services.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(id)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::commentToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CommentDto create(CommentCreateDto commentCreateDto) {
        Comment savedComment = saveCreate(commentCreateDto);
        return commentMapper.commentToDto(savedComment);
    }

    @Transactional
    @Override
    public CommentDto update(CommentUpdateDto commentUpdateDto) {
        if (commentRepository.findById(commentUpdateDto.getId()).isEmpty())
            throw new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(commentUpdateDto.getId()));
        Comment savedComment = saveUpdate(commentUpdateDto);
        return commentMapper.commentToDto(savedComment);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    protected Comment saveUpdate(CommentUpdateDto dto) {
        return commentRepository.save(commentMapper.updateDtoToComment(dto));
    }

    protected Comment saveCreate(CommentCreateDto dto) {
        return commentRepository.save(commentMapper.createDtoToComment(dto));
    }
}
