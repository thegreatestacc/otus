package org.example.hw_10.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_10.dto.book.BookCreateDto;
import org.example.hw_10.dto.book.BookDto;
import org.example.hw_10.dto.book.BookUpdateDto;
import org.example.hw_10.exceptions.NotFoundException;
import org.example.hw_10.mappers.BookMapper;
import org.example.hw_10.models.Book;
import org.example.hw_10.repositories.BookRepository;
import org.example.hw_10.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final static String BOOK_NOT_FOUND_MESSAGE = "Book with id %d not found!";

    @Transactional(readOnly = true)
    @Override
    public BookDto findById(long id) {
        return bookRepository.findById(id)
                .map(bookMapper::convertBookToDto)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(id)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::convertBookToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookDto create(BookCreateDto bookCreateDto) {
        Book savedBook = save(bookCreateDto);
        return bookMapper.convertBookToDto(savedBook);
    }

    @Transactional
    @Override
    public BookDto update(BookUpdateDto bookUpdateDto) {
        if (bookRepository.findById(bookUpdateDto.getBookId()).isEmpty())
            throw new NotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(bookUpdateDto.getBookId()));
        Book savedBook = save(bookUpdateDto);
        return bookMapper.convertBookToDto(savedBook);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    protected Book save(BookDto bookDto) {
        return bookRepository.save(bookMapper.convertDtoToBook(bookDto));
    }
}
