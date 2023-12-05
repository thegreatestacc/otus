package com.example.hw_7.converts;

import com.example.hw_7.models.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
