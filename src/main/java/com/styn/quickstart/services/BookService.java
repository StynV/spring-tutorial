package com.styn.quickstart.services;

import com.styn.quickstart.domain.Book;

public interface BookService {
    
    Book createBook(String isbn, Book book);
}
