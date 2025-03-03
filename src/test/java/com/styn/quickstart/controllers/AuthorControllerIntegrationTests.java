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
import com.styn.quickstart.domain.dto.AuthorDto;
import com.styn.quickstart.services.AuthorService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
    
    @Autowired
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);

        String authorJSON = objectMapper.writeValueAsString(author);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);

        String authorJSON = objectMapper.writeValueAsString(author);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value("nameA")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(81)
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].name").value("nameA")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].age").value(81)
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors/" + author.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/authors/" + author.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value("nameA")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(81)
        );
    }

    @Test
    public void testThatUpdateAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
        AuthorDto author = TestDataUtil.createTestAuthorDtoA();
        String authorJSON = objectMapper.writeValueAsString(author);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatUpdateAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        Author savedAuthor = authorService.save(author);

        AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
        String authorJSON = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatUpdateAuthorReturnsAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        Author savedAuthor = authorService.save(author);

        AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
        authorDto.setName("updatedNameA");
        String authorJSON = objectMapper.writeValueAsString(authorDto);
        
        mockMvc.perform(
            MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value("updatedNameA")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.age").value(81)
        );
    }
}
