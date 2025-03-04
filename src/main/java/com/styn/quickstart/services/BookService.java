package com.styn.quickstart.services;

import java.util.List;
import java.util.Optional;

import com.styn.quickstart.domain.Book;

public interface BookService {
    
    Book save(String isbn, Book book);

    List<Book> findAll();
    
    Optional<Book> findOne(String isbn);

    boolean isExists(String isbn);

    Book partialUpdate(String isbn, Book book);
}
