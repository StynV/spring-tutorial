package com.styn.quickstart;

import java.util.ArrayList;

import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.Book;

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

    public static Book createTestBookA(Author author) {
        return Book.builder()
            .title("titleA")
            .author(author)
            .build();
    }

    public static Book createTestBookB(Author author) {
        return Book.builder()
            .title("titleB")
            .author(author)
            .build();
    }

    public static Book createTestBookC(Author author) {
        return Book.builder()
            .title("titleC")
            .author(author)
            .build();
    }
}
