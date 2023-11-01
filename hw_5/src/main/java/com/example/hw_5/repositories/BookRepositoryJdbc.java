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
        return Optional.ofNullable(jdbcTemplate.queryForObject("select TITLE, AUTHOR_ID, GENRE_ID where ID = ?", new BookRowMapper()));
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("select ID, TITLE, AUTHOR_ID, GENRE_ID from BOOKS", new BookRowMapper());
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
        parameter.addValue("Author", book.getAuthor());
        parameter.addValue("Genre", book.getGenre());

        jdbcTemplate.update("insert into BOOKS (title, author_id, genre_id) " +
                "values (:title, :author_id, :genre_id)", parameter, keyHolder, new String[]{"id"});

        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        jdbcTemplate.update("insert into BOOKS (id, TITLE, AUTHOR_ID, GENRE_ID) values (?, ?, ?, ?)",
                book.getId(), book.getTitle(), book.getAuthor(), book.getGenre());
        return book;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getObject("AUTHOR_ID", Author.class),
                    rs.getObject("GENRE_ID", Genre.class));
        }
    }
}
