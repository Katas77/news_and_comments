package com.example.NewsAndComments.service.impl;


import com.example.NewsAndComments.model.News;
import com.example.NewsAndComments.repository.NewsRepository;
import com.example.NewsAndComments.service.NewsService;
import com.example.NewsAndComments.utils.BeanUtils;
import com.example.NewsAndComments.web.dto.news.NewsFilter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private boolean check = false;

    @Override
    public List<News> findAll(int pageNumber, int pageSize) {
        return newsRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("News with ID {0} not found", id)));
    }

    @Override
    public ResponseEntity<String> save(News news) {
        newsRepository.save(news);
        return ResponseEntity.ok(MessageFormat.format("News with Text -    {0} save", news.getText()));
    }


    @Override
    public ResponseEntity<String> update(News news) {
        if (check) {
            check=false;
            return ResponseEntity.ok("Editing and deleting a news item is permitted only to the user who created it");
        }
        Optional<News> existedNews = newsRepository.findById(news.getId());
        if (existedNews.isPresent()) {
            BeanUtils.copyNonNullProperties(news, existedNews.get());
            newsRepository.save(existedNews.get());
            return ResponseEntity.ok(MessageFormat.format("News with ID {0} updated", news.getId()));
        } else {
            return ResponseEntity.ok(MessageFormat.format("News with ID {0} not found", news.getId()));
        }
    }


    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if (check) {
            check=false;
            return ResponseEntity.ok("Editing and deleting a news item is permitted only to the user who created it");
        }
        Optional<News> newsRepositoryById = newsRepository.findById(id);
        if (newsRepositoryById.isEmpty()) {
            return ResponseEntity.ok(MessageFormat.format("News with ID {0} not found", id));
        } else {
            newsRepository.deleteById(id);
            return ResponseEntity.ok(MessageFormat.format("News with ID {0} deleted", id));
        }


    }

    @Override
    public List<News> filterByCategory(String category) {
        NewsFilter filter = new NewsFilter(category, null);
        int count = newsRepository.findAllByCategory(filter.getCategory()).size();
        if (count == 0) {
            log.info("News with this category have not yet been created");
            return null;
        } else {
            return newsRepository.findAllByCategory(filter.getCategory());
        }
    }

    @Override
    public List<News> filterByUser_id(Long userId) {
        NewsFilter filter = new NewsFilter(null, userId);
        int count = newsRepository.findAllByUser_id(filter.getUser_id()).size();
        if (count == 0) {
            throw new EntityNotFoundException(MessageFormat.format("The user with ID {0} did not create the news", userId));
        }
        return newsRepository.findAllByUser_id(filter.getUser_id());
    }

    @Override
    public void setCheck(Boolean aBoolean) {
        check = aBoolean;
    }


}
