package org.example.hw_8.services.implementation;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.models.Comment;
import org.example.hw_8.repositories.CommentRepository;
import org.example.hw_8.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository repository;

    @Override
    public Optional<Comment> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Comment insert(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Optional<Comment> optionalComment = repository.findById(comment.getId());


        return null;
    }

    @Override
    public void delete(String id) {

    }
}
