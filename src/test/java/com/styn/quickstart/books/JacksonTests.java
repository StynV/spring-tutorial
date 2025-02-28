package com.styn.quickstart.books;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.styn.quickstart.domain.APIBook;

public class JacksonTests {
    
    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        APIBook book = APIBook.builder()
        .isbn("isbn")
        .title("title")
        .author("author")
        .yearPublished("yearPublished")
        .build();

        String result = objectMapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"isbn\":\"isbn\",\"title\":\"title\",\"author\":\"author\",\"year\":\"yearPublished\"}");
    }

    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() throws JsonProcessingException {
        String json = "{\"isbn\":\"isbn\",\"title\":\"title\",\"author\":\"author\",\"year\":\"yearPublished\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        APIBook result = objectMapper.readValue(json, APIBook.class);

        assertThat(result).isEqualTo(APIBook.builder()
            .isbn("isbn")
            .title("title")
            .author("author")
            .yearPublished("yearPublished")
            .build()
        );
    }
}
