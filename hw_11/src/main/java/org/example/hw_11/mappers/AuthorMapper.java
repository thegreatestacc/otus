package org.example.hw_11.mappers;

import org.example.hw_11.dto.author.AuthorDto;
import org.example.hw_11.models.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public Author dtoToAuthor(AuthorDto dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setFullName(dto.getFullName());
        return author;
    }

    public AuthorDto authorToDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setFullName(author.getFullName());
        return dto;
    }
}
