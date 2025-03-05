package com.styn.quickstart.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.styn.quickstart.TestDataUtil;
import com.styn.quickstart.domain.Author;
import com.styn.quickstart.domain.Book;
import com.styn.quickstart.domain.dto.AuthorDto;
import com.styn.quickstart.domain.dto.BookDto;
import com.styn.quickstart.services.AuthorService;
import com.styn.quickstart.services.BookService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testThatCreateBookReturnsHttp201Created() throws Exception {
        BookDto book = TestDataUtil.createTestBookDto(null);
        String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookReturnsSavedBook() throws Exception {
        BookDto book = TestDataUtil.createTestBookDto(null);

        String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").exists()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        );
    }

    @Test
    public void testThatCreateBookWithNewAuthorReturnsSavedBook() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
        BookDto book = TestDataUtil.createTestBookDto(authorDto);

        String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").exists()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.author.name").value(author.getName())
        );
    }

    @Test
    public void testThatCreateBookWithExistingAuthorReturnsSavedBook() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
        BookDto book = TestDataUtil.createTestBookDto(authorDto);

        String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").exists()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.author.name").value(authorDto.getName())
        );
    }

    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/books")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListBooksReturnsListOfBooks() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/books")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.content[0].isbn").exists()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.content[0].title").value(book.getTitle())
        );
    }

    @Test
    public void testThatGetBookReturnsHttpStatus200WhenBookExists() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/books/" + book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetBookReturnsHttpStatus404WhenNoBookExists() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }


    @Test
    public void testThatGetBookReturnsBook() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/books/" + book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").exists()
            ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        );
    }
    
    @Test
    public void testThatUpdateBooksReturnsHttpStatus200() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        Book savedBook = bookService.save(book.getIsbn(), book);

        BookDto bookDto = TestDataUtil.createTestBookDto(null);
        book.setIsbn(savedBook.getIsbn());
        String bookJSON = objectMapper.writeValueAsString(bookDto);
        
        mockMvc.perform(
            MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatUpdateBooksReturnsUpdatedBook() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        Book savedBook = bookService.save(book.getIsbn(), book);

        BookDto bookDto = TestDataUtil.createTestBookDto(null);
        bookDto.setTitle("updatedTitleA");
        String bookJSON = objectMapper.writeValueAsString(bookDto);
        
        mockMvc.perform(
            MockMvcRequestBuilders.put("/books/" + savedBook.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").exists()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }

    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus404WhenNoBookExists() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(
            MockMvcRequestBuilders.patch("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus200WhenBookExists() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        Book savedBook = bookService.save(book.getIsbn(), book);

        BookDto bookDto = TestDataUtil.createTestBookDto(null);
        String bookJSON = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
            MockMvcRequestBuilders.patch("/books/" + savedBook.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateBookReturnsBook() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        Book savedBook = bookService.save(book.getIsbn(), book);

        BookDto bookDto = TestDataUtil.createTestBookDto(null);
        bookDto.setIsbn(null);
        bookDto.setTitle("updatedNameAPartial");
        String bookJSON = objectMapper.writeValueAsString(bookDto);
        
        mockMvc.perform(
            MockMvcRequestBuilders.patch("/books/" + savedBook.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.isbn").exists()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value("updatedNameAPartial")
        );
    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus204ForNonExistingBook() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/books/isbn-1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus204ForExistingBook() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        Book savedBook = bookService.save(book.getIsbn(), book);

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/books/" + savedBook.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
