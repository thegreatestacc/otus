package org.example.hw_11.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_11.dto.book.BookCreateDto;
import org.example.hw_11.dto.book.BookDto;
import org.example.hw_11.dto.book.BookUpdateDto;
import org.example.hw_11.exceptions.NotFoundException;
import org.example.hw_11.mappers.BookMapper;
import org.example.hw_11.models.Book;
import org.example.hw_11.repositories.BookRepository;
import org.example.hw_11.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final static String BOOK_NOT_FOUND_MESSAGE = "Book with id %d not found!";

    @Transactional(readOnly = true)
    @Override
    public Mono<BookDto> findById(long id) {
        return bookRepository.findById(id)
                .map(bookMapper::convertBookToDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<BookDto> findAll() {
        return bookRepository.findAll()
                .map(bookMapper::convertBookToDto);
    }

    @Transactional
    @Override
    public BookDto create(BookCreateDto bookCreateDto) {
        Book savedBook = saveCreate(bookCreateDto);
        return bookMapper.convertBookToDto(savedBook);
    }

    @Transactional
    @Override
    public BookDto update(BookUpdateDto bookUpdateDto) {
        if (bookRepository.findById(bookUpdateDto.getBookId()).blockOptional().isEmpty())
            throw new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(bookUpdateDto.getBookId()));
        Book savedBook = saveUpdate(bookUpdateDto);
        return bookMapper.convertBookToDto(savedBook);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    protected Book saveCreate(BookCreateDto dto) {
        return bookRepository.save(bookMapper.convertCreateDtoToBook(dto))
                .blockOptional()
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(dto.getBookId())));
    }

    @Transactional
    protected Book saveUpdate(BookUpdateDto dto) {
        return bookRepository.save(bookMapper.convertUpdateDtoToBook(dto))
                .blockOptional()
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(dto.getBookId())));
    }
}
