package com.example.hw_6.repositories;

import com.example.hw_6.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class GenreRepositoryJdbc implements GenreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public GenreRepositoryJdbc(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> result = entityManager.createQuery("select * from genres", Genre.class);
        return result.getResultList();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }
}
