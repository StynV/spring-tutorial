package com.styn.quickstart.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.styn.quickstart.TestDataUtil;
import com.styn.quickstart.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSQL() {
        Book book = TestDataUtil.createTestBookA();

        underTest.create(book);

        verify(jdbcTemplate).update(
            eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
            eq("isbnA"), eq("titleA"), eq(1L)
        );
    }
    
    @Test
    public void testThatFindOneGeneratesTheCorrectSQL() {
        underTest.findOne("isbn");

        verify(jdbcTemplate).query(
            eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
            ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
            eq("isbn")
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSQL() {
        underTest.find();

        verify(jdbcTemplate).query(
            eq("SELECT isbn, title, author_id FROM books"),
            ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }
}
