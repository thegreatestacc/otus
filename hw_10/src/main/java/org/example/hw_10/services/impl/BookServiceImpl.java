package org.example.hw_10.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.dto.BookCreateDto;
import org.example.hw_10.dto.BookDto;
import org.example.hw_10.dto.BookUpdateDto;
import org.example.hw_10.exceptions.NotFoundException;
import org.example.hw_10.models.Author;
import org.example.hw_10.models.Book;
import org.example.hw_10.models.Genre;
import org.example.hw_10.repositories.AuthorRepository;
import org.example.hw_10.repositories.BookRepository;
import org.example.hw_10.repositories.GenreRepository;
import org.example.hw_10.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    private final static String BOOK_NOT_FOUND_MESSAGE = "Book with id %d not found!";
    private final static String AUTHOR_NOT_FOUND_MESSAGE = "Author with id %d not found!";
    private final static String GENRE_NOT_FOUND_MESSAGE = "Genre with id %d not found!";

    @Transactional(readOnly = true)
    @Override
    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(id)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Book insert(BookCreateDto bookCreateDto) {
        return save(bookCreateDto);
    }

    @Transactional
    @Override
    public Book update(BookUpdateDto bookUpdateDto) {
        if (bookRepository.findById(bookUpdateDto.getBookId()).isEmpty())
            throw new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(bookUpdateDto.getBookId()));
        return save(bookUpdateDto);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    protected Book save(BookDto bookDto) {
        return bookRepository.save(convertDtoToBook(bookDto));
    }

    @Transactional
    public Book convertDtoToBook(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(bookDto.getAuthorId())));
        Genre genre = genreRepository.findById(bookDto.getGenreId())
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND_MESSAGE.formatted(bookDto.getGenreId())));
        return new Book(bookDto.getBookId(), bookDto.getTitle(), author, genre);
    }
}
