package com.styn.quickstart.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.Book;
import com.styn.quickstart.repositories.AuthorRepository;
import com.styn.quickstart.repositories.BookRepository;
import com.styn.quickstart.services.BookService;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    
    private AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public Book save(String isbn, Book book) {
        if (book.getAuthor() != null) {
            Optional<Author> author = authorRepository.findByName(book.getAuthor().getName());
        
            if (author.isPresent()) {
                book.setAuthor(author.get());
            } else {
                Author newAuthor = authorRepository.save(book.getAuthor());
                book.setAuthor(newAuthor);
            }
        } 
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
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public Book partialUpdate(String isbn, Book book) {
        book.setIsbn(isbn);

        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(book.getTitle()).ifPresent(existingBook::setTitle);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book does not exist."));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
