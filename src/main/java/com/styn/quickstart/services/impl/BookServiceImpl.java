package com.styn.quickstart.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.styn.quickstart.domain.Book;
import com.styn.quickstart.repositories.BookRepository;
import com.styn.quickstart.services.BookService;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public Book createBook(String isbn, Book book) {
        book.setIsbn(isbn);
        
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return StreamSupport.stream(bookRepository
                .findAll()
                .spliterator(),
                false)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }
}
