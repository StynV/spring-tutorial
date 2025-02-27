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

    public static Book createTestBook() {
        return Book.builder()
            .isbn("isbn")
            .title("title")
            .author_id(1L)
            .build();
    }
}
