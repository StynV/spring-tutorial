package com.styn.quickstart.services;

import java.util.List;
import java.util.Optional;

import com.styn.quickstart.domain.Author;

public interface AuthorService {
    Author save(Author author);

    List<Author> findAll();

    Optional<Author> findOne(Long id);

    boolean isExists(Long id);
}
