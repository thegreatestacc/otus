package org.example.hw_8.converters;

import org.example.hw_8.models.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, full name: &s".formatted(author.getId(), author.getFullName());
    }
}
