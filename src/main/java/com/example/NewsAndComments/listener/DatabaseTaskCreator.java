

package com.example.NewsAndComments.listener;


import com.example.NewsAndComments.model.Comment;
import com.example.NewsAndComments.model.News;
import com.example.NewsAndComments.model.User;
import com.example.NewsAndComments.service.CommentService;
import com.example.NewsAndComments.service.NewsService;
import com.example.NewsAndComments.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseTaskCreator {
    private final CommentService commentService;
    private final NewsService newsService;
    private final UserService userService;


    @EventListener(ApplicationStartedEvent.class)
    public void createTaskData() {
        log.debug("Calling DatabaseTaskCreator->createTaskData...");
        ArrayList<News> newsArrayList = new ArrayList<>();
        ArrayList<Comment> commentArrayList = new ArrayList<>();

        User petrov = User.builder().nickname("Petruho").build();
        News spartak = News.builder().user(petrov).text("Spartak lost to Dynamo").category("Sport").build();
        News afrika = News.builder().user(petrov).text("Vegetation is represented by sugar cane, coconut palm, banana, coffee tree, and cloves.").category("Nature").build();
        newsArrayList.add(spartak);
        newsArrayList.add(afrika);

        Comment petrovSpartak = Comment.builder().user(petrov).text("Bad or good ? i don't know").news(spartak).build();
        Comment petrovAfrika = Comment.builder().user(petrov).text("I love nature").news(afrika).build();
        commentArrayList.add(petrovAfrika);
        commentArrayList.add(petrovAfrika);
        petrov.setNewstList(newsArrayList);
        petrov.setCommentList(commentArrayList);

        userService.save(petrov);
        newsService.save(spartak);
        newsService.save(afrika);
        commentService.save(petrovAfrika);
        commentService.save(petrovSpartak);
    }
}

