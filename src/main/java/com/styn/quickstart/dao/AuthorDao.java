package com.styn.quickstart.dao;

import java.util.List;
import java.util.Optional;

import com.styn.quickstart.domain.Author;

public interface AuthorDao {
    
    void create(Author author);

    Optional<Author> findOne(long authorId);

    List<Author> find();

    void update(Long id, Author author);
}
