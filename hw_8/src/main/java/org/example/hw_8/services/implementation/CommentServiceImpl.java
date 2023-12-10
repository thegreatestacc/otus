package org.example.hw_8.services.implementation;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.models.Comment;
import org.example.hw_8.repositories.CommentRepository;
import org.example.hw_8.services.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Comment insert(Comment comment) {
        return save(comment.getComment());
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        Comment commentForUpdating = null;
        Optional<Comment> optionalComment = repository.findById(comment.getId());

        if (optionalComment.isPresent()) {
            commentForUpdating = optionalComment.get();
            commentForUpdating.setComment(comment.getComment());
            save(commentForUpdating.getComment());
        }
        return commentForUpdating;
    }

    @Transactional
    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    public Comment save(String text) {
        Comment comment = new Comment();
        comment.setComment(text);
        return repository.save(comment);
    }
}
