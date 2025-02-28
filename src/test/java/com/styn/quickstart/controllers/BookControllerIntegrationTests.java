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
import com.styn.quickstart.domain.dto.BookDto;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    
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
    public void testThatCreateBookReturnsSavedAuthor() throws Exception {
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
}
