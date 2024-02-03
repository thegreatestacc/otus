package org.example.hw_10.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.exceptions.NotFoundException;
import org.example.hw_10.models.Comment;
import org.example.hw_10.repositories.CommentRepository;
import org.example.hw_10.services.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private static final String COMMENT_NOT_FOND_MESSAGE = "Comment with id %d not found!";

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }


    @Transactional
    @Override
    public Comment insert(Comment comment) {
        Optional<Comment> optionalComment = commentRepository.findById(comment.getId());
        if (optionalComment.isPresent())
            return commentRepository.save(comment);
        else throw new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(comment.getId()));
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        Optional<Comment> optionalComment = commentRepository.findById(comment.getId());
        if (optionalComment.isPresent()) {
            Comment updatedComment = optionalComment.get();
            updatedComment.setComment(comment.getComment());
            commentRepository.save(updatedComment);
            return updatedComment;
        } else throw new NotFoundException(COMMENT_NOT_FOND_MESSAGE.formatted(comment.getId()));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
