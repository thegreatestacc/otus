package com.example.hw_7.services;

import com.example.hw_7.models.Comment;
import com.example.hw_7.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

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
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        Optional<Comment> optionalComment = commentRepository.findById(comment.getId());

        if (optionalComment.isPresent()) {
            Comment updatedComment = optionalComment.get();
            updatedComment.setComment(comment.getComment());
            return updatedComment;
        } else {
            throw new RuntimeException("Comment does not exist");
        }
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.delete(id);
    }
}
