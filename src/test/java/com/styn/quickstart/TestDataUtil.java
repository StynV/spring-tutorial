package com.styn.quickstart;

import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.Book;

public class TestDataUtil {

    private TestDataUtil() {}

    public static Author createTestAuthor() {
        return Author.builder()
            .id(1L)
            .name("name")
            .age(80)
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
