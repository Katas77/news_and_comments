package com.example.NewsAndComments.service;


import com.example.NewsAndComments.model.News;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NewsService {
    List<News> findAll(int pageNumber,int pageSize);

    News findById(Long id);

    ResponseEntity<String> save(News news);

    ResponseEntity<String> update(News news);

    ResponseEntity<String> deleteById(Long id);
    List<News> filterByCategory(String Category);
    List<News> filterByUser_id(Long User_id);
    void setCheck(Boolean aBoolean);
}
