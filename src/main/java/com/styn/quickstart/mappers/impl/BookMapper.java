package com.styn.quickstart.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.styn.quickstart.domain.Book;
import com.styn.quickstart.domain.dto.BookDto;
import com.styn.quickstart.mappers.Mapper;

@Component
public class BookMapper implements Mapper<Book, BookDto> {

    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public Book mapFrom(BookDto book) {
        return modelMapper.map(book, Book.class);
    }
    
}
