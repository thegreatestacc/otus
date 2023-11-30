package com.example.hw_6.repositories;

import com.example.hw_6.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JpaBookRepository implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Book> findById(long id) {

        var query = "select BOOKS.ID, TITLE, AUTHOR_ID, FULL_NAME, GENRE_ID, NAME\n" +
                "    FROM BOOKS\n" +
                "        INNER JOIN PUBLIC.AUTHORS A on A.ID = BOOKS.AUTHOR_ID\n" +
                "        INNER JOIN PUBLIC.GENRES G on G.ID = BOOKS.GENRE_ID\n" +
                "        WHERE BOOKS.ID = :ID";

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-entity-graph");

        TypedQuery<Book> typedQuery = entityManager.createQuery(query, Book.class);

        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);

        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public List<Book> findAll() {
        var query = "select BOOKS.ID, TITLE, AUTHOR_ID, FULL_NAME, GENRE_ID, NAME\n" +
                "FROM BOOKS\n" +
                "         INNER JOIN PUBLIC.AUTHORS A on A.ID = BOOKS.AUTHOR_ID\n" +
                "         INNER JOIN PUBLIC.GENRES G on G.ID = BOOKS.GENRE_ID;";

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-entity-graph");

        TypedQuery<Book> typedQuery = entityManager.createQuery(query, Book.class);

        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);

        return typedQuery.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
    }

    @Transactional
    public Book insert(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Transactional
    public Book update(Book book) {
        Book bookForUpdate = entityManager.find(Book.class, book);
        bookForUpdate.setTitle(book.getTitle());
        bookForUpdate.setAuthor(book.getAuthor());
        bookForUpdate.setGenre(book.getGenre());
        entityManager.refresh(bookForUpdate);
        return bookForUpdate;
    }
}
