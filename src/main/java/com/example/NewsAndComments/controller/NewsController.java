
package com.example.NewsAndComments.controller;

import com.example.NewsAndComments.aop.AllowedNewsUpdate;
import com.example.NewsAndComments.aop.AllowedNewsDell;
import com.example.NewsAndComments.service.NewsService;
import com.example.NewsAndComments.web.mapper.NewsMapper;
import com.example.NewsAndComments.web.dto.news.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<NewsListResponse> findAll(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(newsMapper.newsListResponseList(newsService.findAll(pageNumber, pageSize)));
    }

    @GetMapping("/filter/category/{category}")
    public ResponseEntity<NewsListResponse> filterByCategory(@PathVariable String category) {
        return ResponseEntity.ok(newsMapper.newsListResponseList(newsService.filterByCategory(category)));
    }

    @GetMapping("/filter/user_id/{user_id}")
    public ResponseEntity<NewsListResponse> filterByUser_id(@PathVariable long user_id) {
        return ResponseEntity.ok(newsMapper.newsListResponseList(newsService.filterByUser_id(user_id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseFindById> findById(@PathVariable long id) {
        return ResponseEntity.ok(newsMapper.newsToResponseAllField(newsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid UpsertNewsRequest request) {
        return newsService.save(newsMapper.requestToNews(request));
    }

    @AllowedNewsUpdate
    @PutMapping("/{newsId}")
    public ResponseEntity<String> update(@PathVariable("newsId") long newsId, @Valid @RequestBody UpsertNewsRequest request) {
        return newsService.update(newsMapper.requestNews(newsId, request));
    }

    @AllowedNewsDell
    @DeleteMapping("/{id}/{nickname}")
    public ResponseEntity<String> delete(@PathVariable Long id, @PathVariable String nickname) {
        return newsService.deleteById(id);
    }


}

