package org.example.hw_8.converters;

import org.example.hw_8.models.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, genre name: %s".formatted(genre.getId(), genre.getName());
    }
}
