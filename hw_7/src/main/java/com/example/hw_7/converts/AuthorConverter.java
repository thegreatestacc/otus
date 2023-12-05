package com.example.hw_7.converts;

import com.example.hw_7.models.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
