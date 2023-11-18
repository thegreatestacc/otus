package com.example.hw_5.converters;

import com.example.hw_5.models.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
