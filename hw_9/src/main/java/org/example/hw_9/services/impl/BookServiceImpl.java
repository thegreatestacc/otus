package org.example.hw_9.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_9.exceptions.NotFoundException;
import org.example.hw_9.models.Book;
import org.example.hw_9.repositories.AuthorRepository;
import org.example.hw_9.repositories.BookRepository;
import org.example.hw_9.repositories.GenreRepository;
import org.example.hw_9.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Book insert(long id, String title, long authorId, long genreId) {
        return save(id, title, authorId, genreId);
    }

    @Transactional
    @Override
    public Book update(long id, String title, long authorId, long genreId) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(id)));
        return save(book.getId(),
                book.getTitle(),
                book.getAuthor().getId(),
                book.getGenre().getId());
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Book save(long id, String title, long authorId, long genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND_MESSAGE.formatted(genreId)));
        var book = new Book(id, title, author, genre);
        return bookRepository.save(book);
    }
}
