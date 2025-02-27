package com.styn.quickstart.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.styn.quickstart.dao.BookDao;
import com.styn.quickstart.domain.Book;

public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
            "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
            book.getIsbn(), book.getTitle(), book.getAuthor_id()
        );
    }
}
