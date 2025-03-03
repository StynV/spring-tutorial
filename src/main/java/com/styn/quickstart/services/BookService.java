package com.styn.quickstart.services;

import java.util.List;

import com.styn.quickstart.domain.Book;

public interface BookService {
    
    Book createBook(String isbn, Book book);

    List<Book> findAll();

}
