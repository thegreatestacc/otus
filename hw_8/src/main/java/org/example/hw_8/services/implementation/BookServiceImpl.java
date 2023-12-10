package org.example.hw_8.services.implementation;

import lombok.RequiredArgsConstructor;
import org.example.hw_8.exceptions.NotFoundException;
import org.example.hw_8.models.Author;
import org.example.hw_8.models.Book;
import org.example.hw_8.models.Genre;
import org.example.hw_8.repositories.AuthorRepository;
import org.example.hw_8.repositories.BookRepository;
import org.example.hw_8.repositories.GenreRepository;
import org.example.hw_8.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Book insert(String title, String authorId, String genreId) {
        return save(UUID.randomUUID().toString(), title, authorId, genreId);
    }

    @Transactional
    @Override
    public Book update(String id, String title, String authorId, String genreId) {
        var optionalBook = bookRepository.findById(id);
        optionalBook.orElseThrow(() -> new NotFoundException("Book with id %s already exist!".formatted(id)));
        var book = optionalBook.get();
        return save(book.getId(), book.getTitle(), book.getAuthor().getId(), book.getGenre().getId());
    }

    @Transactional
    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Book save(String id, String title, String authorId, String genreId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author with id %s doesn't exist!".formatted(authorId)));
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("Genre with id %s doesn't exist!".formatted(genreId)));
        Book book = new Book(id, title, author, genre);
        return bookRepository.save(book);
    }
}
