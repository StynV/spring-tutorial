package com.styn.quickstart.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIBook {
    private String isbn;
    
    private String title;

    private String author;
    
    @JsonProperty("year")
    private String yearPublished;
}
