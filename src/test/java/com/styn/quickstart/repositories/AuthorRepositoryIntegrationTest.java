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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {
    
    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository authorDaoImpl) {
        this.underTest = authorDaoImpl;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(author.getId());
        assertThat(result.get().getName()).isEqualTo(author.getName());
        assertThat(result.get().getAge()).isEqualTo(author.getAge());
        assertThat(result.get().getBooks().size()).isEqualTo(author.getBooks().size());
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAll();
        List<Author> authors = StreamSupport.stream(result.spliterator(), false).collect(Collectors.toList());
        assertThat(authors).hasSize(3);

        assertThat(authors)
        .extracting(Author::getId, Author::getName, Author::getAge, author -> author.getBooks().size())
        .containsExactlyInAnyOrder(
            tuple(authorA.getId(), authorA.getName(), authorA.getAge(), authorA.getBooks().size()),
            tuple(authorB.getId(), authorB.getName(), authorB.getAge(), authorB.getBooks().size()),
            tuple(authorC.getId(), authorC.getName(), authorC.getAge(), authorC.getBooks().size())
        );
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        authorA.setName("updatedName");
        underTest.save(authorA);

        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(authorA.getId());
        assertThat(result.get().getName()).isEqualTo("updatedName");
        assertThat(result.get().getAge()).isEqualTo(authorA.getAge());
        assertThat(result.get().getBooks().size()).isEqualTo(authorA.getBooks().size());
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        underTest.deleteById(authorA.getId());

        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        Iterable<Author> result = underTest.ageLessThan(50);
        assertThat(result)
            .extracting(Author::getId, Author::getName, Author::getAge)
            .containsExactly(
                tuple(authorB.getId(), authorB.getName(), authorB.getAge()),
                tuple(authorC.getId(), authorC.getName(), authorC.getAge())
            );
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAuthorsWithAgeGreaterThan(50);
        assertThat(result)
            .extracting(Author::getId, Author::getName, Author::getAge)
            .containsExactly(
                tuple(authorA.getId(), authorA.getName(), authorA.getAge())
            );
    }
}
