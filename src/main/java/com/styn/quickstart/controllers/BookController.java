package com.styn.quickstart.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.styn.quickstart.domain.Book;
import com.styn.quickstart.domain.dto.BookDto;
import com.styn.quickstart.mappers.Mapper;
import com.styn.quickstart.services.BookService;

@RestController
public class BookController {

    private Mapper<Book, BookDto> bookMapper;

    private BookService bookService;

    public BookController(Mapper<Book, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(
        @PathVariable("isbn") String isbn,
        @RequestBody BookDto bookDto
    ) {
        Book book = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbn);
        Book savedBook = bookService.save(isbn, book);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);

        if (bookExists) {
            return new ResponseEntity<>( bookMapper.mapTo(savedBook), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/books")
    public List<BookDto> listBooks() {
        List<Book> books = bookService.findAll();
        return books.stream()
            .map(bookMapper::mapTo)
            .collect(Collectors.toList());
    }

    @GetMapping(path = "/books/{id}")
    public ResponseEntity<BookDto> getAuthor(@PathVariable("id") String isbn) {
        Optional<Book> foundBook = bookService.findOne(isbn);
        return foundBook.map(book -> {
            BookDto bookDto = bookMapper.mapTo(book);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(
        @PathVariable("isbn") String isbn,
        @RequestBody BookDto bookDto
    ) {
        if (!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book book = bookMapper.mapFrom(bookDto);
        Book updatedBook = bookService.partialUpdate(isbn, book);
        return new ResponseEntity<>(
            bookMapper.mapTo(updatedBook),
            HttpStatus.OK
        );
    }
}
