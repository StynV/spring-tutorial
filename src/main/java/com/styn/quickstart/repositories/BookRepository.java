package com.styn.quickstart.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.styn.quickstart.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
    
}
