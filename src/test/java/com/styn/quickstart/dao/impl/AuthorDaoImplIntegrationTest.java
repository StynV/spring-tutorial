package com.styn.quickstart.dao.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.styn.quickstart.TestDataUtil;
import com.styn.quickstart.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTest {
    
    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl authorDaoImpl) {
        this.underTest = authorDaoImpl;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.create(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.create(authorC);

        Author authorD = TestDataUtil.createTestAuthorD();
        underTest.create(authorD);

        List<Author> result = underTest.find();
        assertThat(result).hasSize(3).contains(authorB, authorC, authorD);
    }
}
