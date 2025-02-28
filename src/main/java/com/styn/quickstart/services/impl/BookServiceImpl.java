package com.styn.quickstart.services.impl;

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
    
}
