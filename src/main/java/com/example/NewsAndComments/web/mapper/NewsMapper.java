package com.example.NewsAndComments.web.mapper;

import com.example.NewsAndComments.model.News;
import com.example.NewsAndComments.web.dto.news.NewsListResponse;
import com.example.NewsAndComments.web.dto.news.NewsResponse;
import com.example.NewsAndComments.web.dto.news.NewsResponseFindById;
import com.example.NewsAndComments.web.dto.news.UpsertNewsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestNews(Long newsId, UpsertNewsRequest request);

    NewsResponse newsToResponse(News news);

    NewsResponseFindById newsToResponseAllField(News news);

    default NewsListResponse newsListResponseList(List<News> newsList) {
        NewsListResponse response = new NewsListResponse();
        response.setNewsResponses(newsList.stream().map(this::newsToResponse).collect(Collectors.toList()));
        return response;
    }
}

