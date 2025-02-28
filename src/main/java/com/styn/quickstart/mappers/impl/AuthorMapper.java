package com.styn.quickstart.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.dto.AuthorDto;
import com.styn.quickstart.mappers.Mapper;

@Component
public class AuthorMapper implements Mapper<Author, AuthorDto> {

    private ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public Author mapFrom(AuthorDto author) {
        return modelMapper.map(author, Author.class);
    }
}
