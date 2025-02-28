package com.styn.quickstart;

import java.util.ArrayList;

import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.Book;
import com.styn.quickstart.domain.dto.AuthorDto;
import com.styn.quickstart.domain.dto.BookDto;

public class TestDataUtil {

    private TestDataUtil() {}

    public static Author createTestAuthorA() {
        return Author.builder()
            .name("nameA")
            .age(81)
            .books(new ArrayList<>())
            .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
            .name("nameB")
            .age(41)
            .books(new ArrayList<>())
            .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
            .name("nameC")
            .age(10)
            .books(new ArrayList<>())
            .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
            .isbn("isbnA")
            .title("titleA")
            .author(author)
            .build();
    }

    public static BookDto createTestBookDto(final AuthorDto author) {
        return BookDto.builder()
            .isbn("isbnA")
            .title("titleA")
            .author(author)
            .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
            .isbn("isbnB")
            .title("titleB")
            .author(author)
            .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
            .isbn("isbnC")
            .title("titleC")
            .author(author)
            .build();
    }
}
