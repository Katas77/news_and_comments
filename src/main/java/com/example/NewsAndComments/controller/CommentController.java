
package com.example.NewsAndComments.controller;


import com.example.NewsAndComments.aop.AllowedCommentDell;
import com.example.NewsAndComments.aop.AllowedCommentUpdate;
import com.example.NewsAndComments.service.CommentService;
import com.example.NewsAndComments.web.mapper.CommentMapper;
import com.example.NewsAndComments.web.dto.comment.CommentListResponse;
import com.example.NewsAndComments.web.dto.comment.CommentResponse;
import com.example.NewsAndComments.web.dto.comment.UpsertCommentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping ("/newsId/{newsId}")
    public ResponseEntity<CommentListResponse> findAll(@PathVariable("newsId") long newsId) {
        return ResponseEntity.ok(commentMapper.commentListResponseList(commentService.findAll(newsId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid UpsertCommentRequest request) {
        return commentService.save(commentMapper.requestToComment(request));
    }

    @PutMapping("/{commentId}")
    @AllowedCommentUpdate
    public ResponseEntity<String> update(@PathVariable("commentId") long commentId, @RequestBody @Valid UpsertCommentRequest request) {
        return commentService.update(commentMapper.requestToComment(commentId, request));
    }

    @DeleteMapping("/{id}/{nickname}")
    @AllowedCommentDell
    public ResponseEntity<String> delete(@PathVariable Long id, @PathVariable String nickname) {
        return commentService.deleteById(id);
    }
}

