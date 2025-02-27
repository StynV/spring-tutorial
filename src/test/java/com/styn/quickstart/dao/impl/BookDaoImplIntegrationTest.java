package com.styn.quickstart.dao.impl;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.styn.quickstart.TestDataUtil;
import com.styn.quickstart.dao.AuthorDao;
import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTest {
    
    private AuthorDao authorDao;
    private BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl bookDaoImpl, AuthorDao authorDao) {
        this.underTest = bookDaoImpl;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthor_id(author.getId());
        underTest.create(book);
        
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
}
