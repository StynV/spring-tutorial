package com.styn.quickstart.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.styn.quickstart.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    
}
