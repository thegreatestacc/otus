package com.example.hw_5.converters;

import com.example.hw_5.models.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
