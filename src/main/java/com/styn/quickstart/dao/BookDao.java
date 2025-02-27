package com.styn.quickstart.dao;

import java.util.List;
import java.util.Optional;

import com.styn.quickstart.domain.Book;

public interface BookDao {
 
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();

}
