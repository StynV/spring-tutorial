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
import com.styn.quickstart.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSQL() {
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(
            eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
            eq(1L), eq("nameA"), eq(81)
        );
    }


    @Test
    public void testThatFindOneGeneratesTheCorrectSQL() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
            eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
            ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
            eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSQL() {
        underTest.find();

        verify(jdbcTemplate).query(
            eq("SELECT id, name, age FROM authors"),
            ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSQL() {
        Author author = TestDataUtil.createTestAuthorA();

        underTest.update(author.getId(), author);

        verify(jdbcTemplate).update(
            "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
            1L, "nameA", 81, 1L
        );
    }
}
