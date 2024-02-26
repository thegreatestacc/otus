package org.example.hw_11.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.book.BookCreateDto;
import org.example.hw_11.dto.book.BookDto;
import org.example.hw_11.dto.book.BookUpdateDto;
import org.example.hw_11.exceptions.NotFoundException;
import org.example.hw_11.mappers.BookMapper;
import org.example.hw_11.models.Book;
import org.example.hw_11.repositories.AuthorRepository;
import org.example.hw_11.repositories.BookRepository;
import org.example.hw_11.repositories.GenreRepository;
import org.example.hw_11.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private final BookMapper bookMapper;

    private final static String BOOK_NOT_FOUND_MESSAGE = "Book with id %d not found!";
    private final static String AUTHOR_NOT_FOUND_MESSAGE = "Author with id %d not found!";
    private final static String GENRE_NOT_FOUND_MESSAGE = "Genre with id %d not found!";

    @Transactional(readOnly = true)
    @Override
    public Mono<BookDto> findById(long id) {
        return bookRepository.findById(id)
                .map(bookMapper::convertBookToDto)
                .switchIfEmpty(Mono.error(new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(id))));
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<BookDto> findAll() {
        return bookRepository.findAll()
                .map(bookMapper::convertBookToDto)
                .switchIfEmpty(Flux.empty());
    }

    @Transactional
    @Override
    public Mono<BookDto> create(BookCreateDto bookCreateDto) {

        return authorRepository.findById(bookCreateDto.getAuthorId())
                .switchIfEmpty(
                        Mono.error(new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(bookCreateDto.getAuthorId())))
                ).zipWith(genreRepository.findById(bookCreateDto.getGenreId()))
                .switchIfEmpty(
                        Mono.error(new NotFoundException(GENRE_NOT_FOUND_MESSAGE.formatted(bookCreateDto.getGenreId())))
                ).flatMap(tuple -> bookRepository.save(
                        new Book(
                                bookCreateDto.getBookId(),
                                bookCreateDto.getTitle(),
                                tuple.getT1(),
                                tuple.getT2()
                        )
                )).map(bookMapper::convertBookToDto);
    }

    @Transactional
    @Override
    public Mono<BookDto> update(BookUpdateDto bookUpdateDto) {

        return bookRepository.findById(bookUpdateDto.getBookId())
                .switchIfEmpty(
                        Mono.error(new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(bookUpdateDto.getBookId())))
                ).zipWith(authorRepository.findById(bookUpdateDto.getAuthorId()))
                .switchIfEmpty(
                        Mono.error(new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE.formatted(bookUpdateDto.getAuthorId())))
                ).zipWith(genreRepository.findById(bookUpdateDto.getGenreId()))
                .switchIfEmpty(
                        Mono.error(new NotFoundException(GENRE_NOT_FOUND_MESSAGE.formatted(bookUpdateDto.getGenreId())))
                )
                .flatMap(tuple -> {
                            tuple.getT1().getT1().setId(bookUpdateDto.getBookId());
                            tuple.getT1().getT1().setTitle(bookUpdateDto.getTitle());
                            tuple.getT1().getT2().setId(bookUpdateDto.getAuthorId());
                            tuple.getT2().setId(bookUpdateDto.getGenreId());
                            return bookRepository.save(tuple.getT1().getT1());
                        }
                ).map(bookMapper::convertBookToDto);
    }

    @Transactional
    @Override
    public Mono<Void> deleteById(long id) {
        return bookRepository.deleteById(id);
    }

}
