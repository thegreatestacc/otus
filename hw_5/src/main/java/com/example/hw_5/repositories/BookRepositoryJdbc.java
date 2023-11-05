package com.example.hw_5.repositories;

import com.example.hw_5.models.Author;
import com.example.hw_5.models.Book;
import com.example.hw_5.models.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJdbc implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select BOOKS.ID, TITLE, AUTHOR_ID, FULL_NAME, GENRE_ID, NAME\n" +
                        "    FROM BOOKS\n" +
                        "        INNER JOIN PUBLIC.AUTHORS A on A.ID = BOOKS.AUTHOR_ID\n" +
                        "        INNER JOIN PUBLIC.GENRES G on G.ID = BOOKS.GENRE_ID\n" +
                        "        WHERE BOOKS.ID = ?", new BookRowMapper(), id));
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(
                "select BOOKS.ID, TITLE, AUTHOR_ID, FULL_NAME, GENRE_ID, NAME\n" +
                        "FROM BOOKS\n" +
                        "         INNER JOIN PUBLIC.AUTHORS A on A.ID = BOOKS.AUTHOR_ID\n" +
                        "         INNER JOIN PUBLIC.GENRES G on G.ID = BOOKS.GENRE_ID;", new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("delete from BOOKS where id = ?", id);
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("title", book.getTitle());
        parameter.addValue("AUTHOR_ID", book.getAuthor().getId());
        parameter.addValue("GENRE_ID", book.getGenre().getId());

        jdbcTemplate.update("insert into BOOKS (title, AUTHOR_ID, GENRE_ID) values (:title, :AUTHOR_ID, :GENRE_ID)", parameter, keyHolder, new String[]{"id"});


        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        jdbcTemplate.update("insert into BOOKS (id, TITLE, AUTHOR_ID, GENRE_ID) values (?, ?, ?, ?)",
                book.getId(), book.getTitle(), book.getAuthor().getId(), book.getGenre().getId());
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
