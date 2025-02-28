package com.styn.quickstart.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.styn.quickstart.TestDataUtil;
import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
    
    private AuthorRepository authorRepository;
    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTest(AuthorRepository authorRepository, BookRepository bookDaoImpl) {
        this.authorRepository = authorRepository;
        this.underTest = bookDaoImpl;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        author = authorRepository.save(author);
    
        Book book = TestDataUtil.createTestBookA(author);
        book = underTest.save(book);
    
        Optional<Book> result = underTest.findById(book.getIsbn());
    
        assertThat(result).isPresent();
        assertThat(result.get().getIsbn()).isEqualTo(book.getIsbn());
        assertThat(result.get().getTitle()).isEqualTo(book.getTitle());
    
        assertThat(result.get().getAuthor().getId()).isEqualTo(author.getId());
        assertThat(result.get().getAuthor().getName()).isEqualTo(author.getName());
        assertThat(result.get().getAuthor().getAge()).isEqualTo(author.getAge());
    
        assertThat(result.get().getAuthor().getBooks().size()).isEqualTo(1);
    }
    

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        author = authorRepository.save(author);

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        Book bookB = TestDataUtil.createTestBookB(author);
        underTest.save(bookB);

        Book bookC = TestDataUtil.createTestBookC(author);
        underTest.save(bookC);

        Iterable<Book> result = underTest.findAll();
        List<Book> books = StreamSupport.stream(result.spliterator(), false).collect(Collectors.toList());

        assertThat(result).hasSize(3);

        assertThat(books)
        .extracting(Book::getIsbn, Book::getTitle, book -> book.getAuthor().getId())
        .containsExactlyInAnyOrder(
            tuple(bookA.getIsbn(), bookA.getTitle(), bookA.getAuthor().getId()),
            tuple(bookB.getIsbn(), bookB.getTitle(), bookB.getAuthor().getId()),
            tuple(bookC.getIsbn(), bookC.getTitle(), bookC.getAuthor().getId())
        );
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthorA();
        author = authorRepository.save(author);

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        bookA.setTitle("updatedTitle");
        underTest.save(bookA);

        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get().getIsbn()).isEqualTo(bookA.getIsbn());
        assertThat(result.get().getTitle()).isEqualTo("updatedTitle");
        assertThat(result.get().getAuthor().getId()).isEqualTo(author.getId());
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthorA();
        author = authorRepository.save(author);

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        underTest.deleteById(bookA.getIsbn());

        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
