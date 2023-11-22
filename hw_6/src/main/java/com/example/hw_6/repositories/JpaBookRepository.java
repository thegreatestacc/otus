package com.example.hw_6.repositories;

import com.example.hw_6.models.Author;
import com.example.hw_6.models.Book;
import com.example.hw_6.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("ID", id);

        Book book;
        try {
            book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        var query = "select BOOKS.ID, TITLE, AUTHOR_ID, FULL_NAME, GENRE_ID, NAME\n" +
                "FROM BOOKS\n" +
                "         INNER JOIN PUBLIC.AUTHORS A on A.ID = BOOKS.AUTHOR_ID\n" +
                "         INNER JOIN PUBLIC.GENRES G on G.ID = BOOKS.GENRE_ID;";
        var result = entityManager.createQuery(query);
        return result.getResultList();
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
        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("ID", id);
        var query = "delete from BOOKS where id = :ID";
        entityManager.remove(query);
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("title", book.getTitle());
        namedParameters.addValue("AUTHOR_ID", book.getAuthor().getId());
        namedParameters.addValue("GENRE_ID", book.getGenre().getId());

        entityManager.refresh("insert into BOOKS (title, AUTHOR_ID, GENRE_ID) " +
                "values (:title, :AUTHOR_ID, :GENRE_ID)");

        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", book.getId());
        namedParameters.addValue("TITLE", book.getTitle());
        namedParameters.addValue("AUTHOR_ID", book.getAuthor().getId());
        namedParameters.addValue("GENRE_ID", book.getGenre().getId());
        var query = "insert into BOOKS (id, TITLE, AUTHOR_ID, GENRE_ID) " +
                "values (:id, :TITLE, :AUTHOR_ID, :GENRE_ID)";
        entityManager.refresh(query);
        return book;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long authorId = rs.getLong("AUTHOR_ID");
            String authorFullName = rs.getString("FULL_NAME");
            var author = new Author(authorId, authorFullName);

            long genreId = rs.getLong("GENRE_ID");
            String genreName = rs.getString("NAME");
            var genre = new Genre(genreId, genreName);

            long bookId = rs.getLong("ID");
            String bookTitle = rs.getString("TITLE");

            return new Book(
                    bookId,
                    bookTitle,
                    author,
                    genre);
        }
    }
}
