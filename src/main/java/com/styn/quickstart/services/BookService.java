package com.styn.quickstart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.styn.quickstart.domain.Book;

public interface BookService {
    
    Book save(String isbn, Book book);

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);
    
    Optional<Book> findOne(String isbn);

    boolean isExists(String isbn);

    Book partialUpdate(String isbn, Book book);

    void delete(String isbn);
}
