package com.example.NewsAndComments.web.dto.news;

import com.example.NewsAndComments.web.dto.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponseFindById {
    private Long id;
    private Long user_id;
    private String category;
    private String text;
    private Long numberOfComments;
    private List<CommentResponse> commentResponses = new ArrayList<>();



}
