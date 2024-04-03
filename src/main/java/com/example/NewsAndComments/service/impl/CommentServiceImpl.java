package com.example.NewsAndComments.service.impl;

import com.example.NewsAndComments.model.Comment;

import com.example.NewsAndComments.repository.CommentRepository;
import com.example.NewsAndComments.service.CommentService;
import com.example.NewsAndComments.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NewsServiceImpl newsService;
    private boolean check = false;

    @Override
    public List<Comment> findAll(Long newsId) {

        return newsService.findById(newsId).getCommentList();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("Comment with ID {0} not found", id)));
    }

    @Override
    public ResponseEntity<String> save(Comment comment) {
        commentRepository.save(comment);
        return ResponseEntity.ok(MessageFormat.format("Comment with Text -    {0} save", comment.getText()));
    }

    @Override
    public ResponseEntity<String> update(Comment comment) {
        if (check) {
            check=false;
            return ResponseEntity.ok("Editing and deleting a news item is permitted only to the user who created it");
        }
        Optional<Comment> existedComment = commentRepository.findById(comment.getId());
        if (existedComment.isPresent()) {
            BeanUtils.copyNonNullProperties(comment, existedComment.get());
            save(existedComment.get());
            return ResponseEntity.ok(MessageFormat.format("Comment with ID {0} updated", comment.getId()));
        } else {
            return ResponseEntity.ok(MessageFormat.format("News with ID {0} not found", comment.getId()));
        }
    }


    @Override
    public void dellAll() {
        commentRepository.deleteAll();
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if (check) {
            check=false;
            return ResponseEntity.ok("Editing and deleting a news item is permitted only to the user who created it");
        }
        Optional<Comment> newsRepositoryById = commentRepository.findById(id);
        if (newsRepositoryById.isEmpty()) {
            return ResponseEntity.ok(MessageFormat.format("Comment with ID {0} not found", id));
        } else {
            commentRepository.deleteById(id);
            return ResponseEntity.ok(MessageFormat.format("Comment with ID {0} deleted", id));
        }
    }
    @Override
    public void setCheck(Boolean aBoolean) {
        check = aBoolean;
    }

}
