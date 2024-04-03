package com.example.NewsAndComments.web.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {

    private Long id;
    private Long user_id;
    private String category;
    private String text;
    private Long numberOfComments;
}
