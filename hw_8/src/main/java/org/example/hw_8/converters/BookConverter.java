package org.example.hw_8.converters;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.models.Book;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookConverter {
    private AuthorConverter authorConverter;
    private GenreConverter genreConverter;

    public String bookToString(Book book) {
        return "Id: %s, title: %s, author: {%s}, genre: [%s]".formatted(
                book.getId(),
                book.getTitle(),
                authorConverter.authorToString(book.getAuthor()),
                genreConverter.genreToString(book.getGenre()));
    }
}
