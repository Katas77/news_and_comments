package com.example.NewsAndComments.repository;

import com.example.NewsAndComments.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CommentRepository extends JpaRepository<Comment, Long> {


}
