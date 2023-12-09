package com.example.hw_7.services;

import com.example.hw_7.exceptions.NotFoundException;
import com.example.hw_7.models.Book;
import com.example.hw_7.repositories.AuthorRepository;
import com.example.hw_7.repositories.BookRepository;
import com.example.hw_7.repositories.CommentRepository;
import com.example.hw_7.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

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
    public Book insert(String title, long authorId, long genreId, long commentId) {
        return save(0, title, authorId, genreId);
    }

    @Transactional
    @Override
    public Book update(long id, String title, long authorId, long genreId, long commentId) {
        var optional = bookRepository.findById(id);
        var book = optional.orElseThrow(() -> new NotFoundException(String.format("Book with id %d already exist", id)));
        return save(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getId(),
                book.getGenre().getId()
        );
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Book save(long id, String title, long authorId, long genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("Genre with id %d not found".formatted(genreId)));
        var comments = commentRepository.findAll();

        var book = new Book(id, title, author, genre, comments);
        return bookRepository.save(book);
    }
}
