package com.example.hw_5.repositories;

import com.example.hw_5.models.Genre;
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
public class GenreRepositoryJdbc implements GenreRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public GenreRepositoryJdbc(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query("select ID, NAME from GENRES", new GenreRowMapper());
    }

    @Override
    public Optional<Genre> findById(long id) {
        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("ID", id);
        var query = "select ID, NAME from GENRES where id = :ID";
        Genre genre;
        try {
            genre = jdbcTemplate.queryForObject(query, namedParameters, new GenreRowMapper());
            return Optional.ofNullable(genre);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }
}
