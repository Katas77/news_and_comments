package com.example.NewsAndComments.web.mapper.impl;

import com.example.NewsAndComments.model.Comment;
import com.example.NewsAndComments.model.News;
import com.example.NewsAndComments.model.User;
import com.example.NewsAndComments.web.mapper.CommentMapper;
import com.example.NewsAndComments.web.mapper.NewsMapper;
import com.example.NewsAndComments.web.mapper.UserMapper;
import com.example.NewsAndComments.web.dto.comment.CommentListResponse;
import com.example.NewsAndComments.web.dto.news.NewsListResponse;
import com.example.NewsAndComments.web.dto.user.UpsertUserRequest;
import com.example.NewsAndComments.web.dto.user.UserResponse;
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
public class UserMapperAllField implements UserMapper {
    private final NewsMapper newsMapper;
    private final CommentMapper commentMapper;

    @Override
    public User requestToUser(UpsertUserRequest request) {
        if (request == null) {
            return null;
        }
        User.UserBuilder user = User.builder();
        user.nickname(request.getNickname());
        return user.build();
    }

    @Override
    public User requestToUser(Long userId, UpsertUserRequest request) {
        if (userId == null && request == null) {
            return null;
        }
        User.UserBuilder user = User.builder();
        if (request != null) {
            user.nickname(request.getNickname());
        }
        user.id(userId);
        return user.build();
    }

    @Override
    public UserResponse userToResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setNickname(user.getNickname());
        if (!user.getCommentList().isEmpty()) {
            List<Comment> commentList = user.getCommentList();
            CommentListResponse commentListResponse = commentMapper.commentListResponseList(commentList);
            userResponse.setCommentResponses(commentListResponse.getCommentResponses());
        }
        if (!user.getNewstList().isEmpty()) {
            List<News> userNewstList = user.getNewstList();
            NewsListResponse listResponse = newsMapper.newsListResponseList(userNewstList);
            userResponse.setNewsResponses(listResponse.getNewsResponses());
        }
        return userResponse;
    }
}
