package com.example.NewsAndComments.repository;


import com.example.NewsAndComments.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {


   Page<User> findAll(Pageable pageable);

}
