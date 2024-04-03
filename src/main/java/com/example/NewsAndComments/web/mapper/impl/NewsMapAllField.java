package com.example.NewsAndComments.web.mapper.impl;

import com.example.NewsAndComments.model.Comment;
import com.example.NewsAndComments.model.News;
import com.example.NewsAndComments.model.User;
import com.example.NewsAndComments.service.UserService;
import com.example.NewsAndComments.web.mapper.CommentMapper;
import com.example.NewsAndComments.web.mapper.NewsMapper;
import com.example.NewsAndComments.web.dto.comment.CommentListResponse;
import com.example.NewsAndComments.web.dto.news.NewsResponse;
import com.example.NewsAndComments.web.dto.news.NewsResponseFindById;
import com.example.NewsAndComments.web.dto.news.UpsertNewsRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-30T17:35:46+0300",
        comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
@Primary
@AllArgsConstructor
public class NewsMapAllField implements NewsMapper {

    private final UserService userService;
    private final CommentMapper commentMapper;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        if (request == null) {
            return null;
        }
        User user = userService.findById(request.getUser_id());
        News.NewsBuilder news = News.builder();
        news.category(request.getCategory());
        news.text(request.getText());
        news.user(user);

        return news.build();
    }

    @Override
    public News requestNews(Long newsId, UpsertNewsRequest request) {
        if (newsId == null && request == null) {
            return null;
        }
        User user = userService.findById(request.getUser_id());
        News.NewsBuilder news = News.builder();
        if (request != null) {
            news.category(request.getCategory());
            news.text(request.getText());
        }
        news.id(newsId);
        news.user(user);
        return news.build();
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        if (news == null) {
            return null;
        }
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());
        newsResponse.setCategory(news.getCategory());
        newsResponse.setText(news.getText());
        newsResponse.setUser_id(news.getUser().getId());
        newsResponse.setNumberOfComments((long) news.getCommentList().size());
        return newsResponse;
    }

    @Override
    public NewsResponseFindById newsToResponseAllField(News news) {
        if (news == null) {
            return null;
        }
        NewsResponseFindById newsResponse = new NewsResponseFindById();
        newsResponse.setId(news.getId());
        newsResponse.setCategory(news.getCategory());
        newsResponse.setText(news.getText());
        newsResponse.setUser_id(news.getUser().getId());
        newsResponse.setNumberOfComments((long) news.getCommentList().size());
        if (!news.getCommentList().isEmpty()) {
            List<Comment> commentList = news.getCommentList();
            CommentListResponse commentListResponse = commentMapper.commentListResponseList(commentList);
            newsResponse.setCommentResponses(commentListResponse.getCommentResponses());
        }
        return newsResponse;
    }
}
