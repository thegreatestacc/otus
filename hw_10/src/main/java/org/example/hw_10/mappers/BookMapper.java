package org.example.hw_10.mappers;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.dto.book.BookDto;
import org.example.hw_10.exceptions.NotFoundException;
import org.example.hw_10.models.Author;
import org.example.hw_10.models.Book;
import org.example.hw_10.models.Genre;
import org.example.hw_10.repositories.AuthorRepository;
import org.example.hw_10.repositories.GenreRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final static String AUTHOR_NOT_FOUND_MESSAGE = "Author with id %d not found!";
    private final static String GENRE_NOT_FOUND_MESSAGE = "Genre with id %d not found!";

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Transactional
    public Book convertDtoToBook(BookDto book) {
        Author author = authorRepository.findById(book.getAuthorId())
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(book.getAuthorId())));
        Genre genre = genreRepository.findById(book.getGenreId())
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND_MESSAGE.formatted(book.getGenreId())));
        return new Book(book.getBookId(), book.getTitle(), author, genre);
    }

    @Transactional
    public BookDto convertBookToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setBookId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setGenreId(book.getGenre().getId());
        return dto;
    }
}
