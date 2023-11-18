package com.example.hw_6.repositories;

import com.example.hw_6.models.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJdbc implements AuthorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AuthorRepositoryJdbc(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query("select ID, FULL_NAME from AUTHORS", new AuthorRowMapper());
    }

    @Override
    public Optional<Author> findById(long id) {

        var query = "select ID, FULL_NAME from AUTHORS where ID = :ID";
        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("ID", id);
        Author author;
        try {
            author = jdbcTemplate.queryForObject(query, namedParameters, new AuthorRowMapper());
            return Optional.ofNullable(author);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("full_name"));
        }
    }
}