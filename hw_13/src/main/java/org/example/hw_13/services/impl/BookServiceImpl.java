package org.example.hw_13.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_13.dto.book.BookCreateDto;
import org.example.hw_13.dto.book.BookDto;
import org.example.hw_13.dto.book.BookUpdateDto;
import org.example.hw_13.exceptions.NotFoundException;
import org.example.hw_13.mappers.BookMapper;
import org.example.hw_13.models.Book;
import org.example.hw_13.repositories.BookRepository;
import org.example.hw_13.services.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasPermission(#bookCreateDto, 'WRITE')")
    @Transactional
    @Override
    public BookDto create(BookCreateDto bookCreateDto) {
        Book savedBook = saveCreate(bookCreateDto);
        return bookMapper.convertBookToDto(savedBook);
    }

    @Transactional
    @Override
    public BookDto update(BookUpdateDto bookUpdateDto) {
        if (bookRepository.findById(bookUpdateDto.getBookId()).isEmpty())
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
        return bookRepository.save(bookMapper.convertCreateDtoToBook(dto));
    }

    @Transactional
    protected Book saveUpdate(BookUpdateDto dto) {
        return bookRepository.save(bookMapper.convertUpdateDtoToBook(dto));
    }
}
