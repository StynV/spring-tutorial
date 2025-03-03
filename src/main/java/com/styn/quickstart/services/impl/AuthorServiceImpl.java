package com.styn.quickstart.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.styn.quickstart.domain.Author;
import com.styn.quickstart.repositories.AuthorRepository;
import com.styn.quickstart.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return StreamSupport.stream(authorRepository
                .findAll()
                .spliterator(),
                false)
            .collect(Collectors.toList());
    }
}
