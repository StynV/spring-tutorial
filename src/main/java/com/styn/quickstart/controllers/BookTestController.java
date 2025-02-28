package com.styn.quickstart.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.styn.quickstart.domain.APIBook;

import lombok.extern.java.Log;

@RestController
@Log
public class BookTestController {
    
    @GetMapping(path = "/test/books")
    public APIBook retrieveBooks() {
        return APIBook.builder()
            .isbn("isbn")
            .title("title")
            .author("author")
            .yearPublished("yearPublished")
            .build();
    }

    @PostMapping(path = "/test/books")
    public APIBook createBook(@RequestBody final APIBook book) {
        log.info("Got book: " + book.toString());
        return book;
    }
}
