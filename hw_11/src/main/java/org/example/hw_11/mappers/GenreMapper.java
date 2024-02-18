package org.example.hw_11.mappers;

import org.example.hw_11.dto.genre.GenreDto;
import org.example.hw_11.models.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public Genre dtoToGenre(GenreDto dto) {
        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
        return genre;
    }

    public GenreDto genreToDto(Genre genre) {
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }
}
