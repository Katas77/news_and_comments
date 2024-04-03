
package com.example.NewsAndComments.aop;

import com.example.NewsAndComments.model.Comment;
import com.example.NewsAndComments.model.News;
import com.example.NewsAndComments.service.CommentService;
import com.example.NewsAndComments.service.NewsService;
import com.example.NewsAndComments.web.dto.comment.UpsertCommentRequest;
import com.example.NewsAndComments.web.dto.news.UpsertNewsRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class AllowedAspect {
    private final NewsService newsService;
    private final CommentService commentService;

    @Before("@annotation(AllowedNewsUpdate)")
    public void checkNewsUpdate(JoinPoint joinPoint) {
        Long newsId = 0L;
        UpsertNewsRequest request = new UpsertNewsRequest();
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            if (object instanceof Long) {
                newsId = (Long) object;
            }
            if (object instanceof UpsertNewsRequest) {
                request = (UpsertNewsRequest) object;
            }
        }
        News news = newsService.findById(newsId);
        if (!(Objects.equals(news.getUser().getId(), request.getUser_id()))) {
            newsService.setCheck(true);
        }
    }

    @Before("@annotation(AllowedNewsDell)")
    public void checkNewsDell(JoinPoint joinPoint) {
        long newsId = 0L;
        String nickname = "";
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            if (object instanceof Long) {
                newsId = (Long) object;
            }
            if (object instanceof String) {
                nickname = (String) object;
            }
        }
        News news = newsService.findById(newsId);
        if (!news.getUser().getNickname().contains(nickname)) {
            newsService.setCheck(true);
        }
    }

    @Before("@annotation(AllowedCommentUpdate)")
    public void checkCommentUpdate(JoinPoint joinPoint) {
        Long commtntId = 0L;
        UpsertCommentRequest request = new UpsertCommentRequest();
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            if (object instanceof Long) {
                commtntId = (Long) object;
            }
            if (object instanceof UpsertCommentRequest) {
                request = (UpsertCommentRequest) object;
            }
        }
        Comment comment = commentService.findById(commtntId);

        if (!(Objects.equals(comment.getUser().getId(), request.getUser_id()))) {
            commentService.setCheck(true);
            System.out.println(comment.getUser().getId() + "     newsService.setCheck(true)        " + request.getUser_id());
        }
    }

    @Before("@annotation(AllowedCommentDell)")
    public void checkCommentDell(JoinPoint joinPoint) {
        long newsId = 0L;
        String nickname = "";
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            if (object instanceof Long) {
                newsId = (Long) object;
            }
            if (object instanceof String) {
                nickname = (String) object;
            }
        }
        Comment comment = commentService.findById(newsId);
        if (!comment.getUser().getNickname().contains(nickname)) {
            commentService.setCheck(true);
        }
    }

}

