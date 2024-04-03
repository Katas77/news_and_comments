package com.example.NewsAndComments.service;


import com.example.NewsAndComments.model.User;
import com.example.NewsAndComments.web.dto.user.UpsertUserRequest;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface UserService {


    List<User> findAll(int pageNumber,int pageSize);

    User findById(Long id);

    ResponseEntity<String> save(User user);

    ResponseEntity<String> update(User user);

    ResponseEntity<String> deleteById(Long id);

    void dellAll();
}
