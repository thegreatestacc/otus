package org.example.hw_10.mappers;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.dto.book.BookCreateDto;
import org.example.hw_10.dto.book.BookDto;
import org.example.hw_10.dto.book.BookUpdateDto;
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
    public Book convertDtoToBook(BookDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(dto.getAuthorId())));
        Genre genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND_MESSAGE.formatted(dto.getGenreId())));
        return new Book(dto.getBookId(), dto.getTitle(), author, genre);
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

    @Transactional
    public Book convertUpdateDtoToBook(BookUpdateDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(dto.getAuthorId())));
        Genre genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND_MESSAGE.formatted(dto.getGenreId())));
        return new Book(dto.getBookId(), dto.getTitle(), author, genre);
    }

    @Transactional
    public BookUpdateDto convertBookToBookUpdateDto(Book book) {
        BookUpdateDto dto = new BookUpdateDto();
        dto.setBookId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setGenreId(book.getGenre().getId());
        return dto;
    }

    @Transactional
    public Book convertCreateDtoToBook(BookCreateDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(dto.getAuthorId())));
        Genre genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND_MESSAGE.formatted(dto.getGenreId())));
        return new Book(dto.getBookId(), dto.getTitle(), author, genre);
    }

    public BookCreateDto convertBookToBookCreateDto(Book book) {
        BookCreateDto dto = new BookCreateDto();
        dto.setBookId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setGenreId(book.getGenre().getId());
        return dto;
    }
}
