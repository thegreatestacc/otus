package com.example.hw_6.converters;

import com.example.hw_6.models.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
