package com.example.NewsAndComments.web.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsFilter {

    private String category;
    private Long user_id;

}
