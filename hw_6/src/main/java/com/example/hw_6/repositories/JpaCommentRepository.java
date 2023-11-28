package com.example.hw_6.repositories;

import com.example.hw_6.models.Comment;
import javax.persistence.*;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JpaCommentRepository implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public Comment save(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("comment", comment.getComment());
        var query = "insert into COMMENTS (comment) values (:comment)";
        entityManager.refresh(query);
        return comment;
    }

    @Override
    public void delete(long id) {
        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("ID", id);
        var query = "delete from COMMENTS where id = :ID";
        entityManager.remove(query);
    }
}
