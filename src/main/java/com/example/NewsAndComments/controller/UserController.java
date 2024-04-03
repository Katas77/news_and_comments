package com.example.NewsAndComments.controller;

import com.example.NewsAndComments.service.UserService;
import com.example.NewsAndComments.web.mapper.UserMapper;
import com.example.NewsAndComments.web.dto.user.UpsertUserRequest;
import com.example.NewsAndComments.web.dto.user.UserListResponse;
import com.example.NewsAndComments.web.dto.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<UserListResponse> findAll(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(userMapper.userListResponseList(userService.findAll(pageNumber, pageSize)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid UpsertUserRequest request) {
        return userService.save(userMapper.requestToUser(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long userId, @RequestBody UpsertUserRequest request) {
        return userService.update(userMapper.requestToUser(userId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return userService.deleteById(id);
    }
}