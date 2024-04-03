package com.example.NewsAndComments.web.mapper;

import com.example.NewsAndComments.model.Comment;
import com.example.NewsAndComments.web.dto.comment.CommentListResponse;
import com.example.NewsAndComments.web.dto.comment.CommentResponse;
import com.example.NewsAndComments.web.dto.comment.UpsertCommentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    Comment requestToComment(UpsertCommentRequest request);

    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);

    CommentResponse commentToResponse(Comment comment);

    default CommentListResponse commentListResponseList(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();
        response.setCommentResponses(comments.stream().map(this::commentToResponse).collect(Collectors.toList()));
        return response;
    }
}
