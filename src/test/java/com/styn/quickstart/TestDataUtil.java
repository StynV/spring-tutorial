package com.styn.quickstart;

import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.Book;

public class TestDataUtil {

    private TestDataUtil() {}

    public static Author createTestAuthorA() {
        return Author.builder()
            .id(1L)
            .name("nameA")
            .age(81)
            .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
            .id(2L)
            .name("nameB")
            .age(81)
            .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
            .id(3L)
            .name("nameC")
            .age(82)
            .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
            .isbn("isbnA")
            .title("titleA")
            .author_id(1L)
            .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
            .isbn("isbnB")
            .title("titleB")
            .author_id(2L)
            .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
            .isbn("isbnC")
            .title("titleC")
            .author_id(3L)
            .build();
    }
}
