package com.styn.quickstart.services;

import java.util.List;

import com.styn.quickstart.domain.Author;

public interface AuthorService {
    Author createAuthor(Author author);

    List<Author> findAll();
}
