package org.example.hw_11.mappers;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.book.BookCreateDto;
import org.example.hw_11.dto.book.BookDto;
import org.example.hw_11.dto.book.BookUpdateDto;
import org.example.hw_11.models.Author;
import org.example.hw_11.models.Book;
import org.example.hw_11.models.Genre;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BookMapper {

    @Transactional
    public Book convertDtoToBook(BookDto dto) {
        return new Book(dto.getBookId(), dto.getTitle(), new Author(), new Genre());
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
        return new Book(dto.getBookId(), dto.getTitle(), new Author(), new Genre());
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
        return new Book(dto.getBookId(), dto.getTitle(), new Author(), new Genre());
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
