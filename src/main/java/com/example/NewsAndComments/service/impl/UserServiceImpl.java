package com.example.NewsAndComments.service.impl;

import com.example.NewsAndComments.model.User;
import com.example.NewsAndComments.repository.UserRepository;
import com.example.NewsAndComments.service.UserService;
import com.example.NewsAndComments.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userRepository.findById(id).get();
        } else {
            log.info(MessageFormat.format("User ID {0} not found", id));
            return null;
        }
    }

    @Override
    public ResponseEntity<String> save(User user) {
        userRepository.save(user);
        return ResponseEntity.ok(MessageFormat.format("User with Nickname   {0} save", user.getNickname()));
    }

    @Override
    public ResponseEntity<String> update(User user) {
        Optional<User> existedUser = userRepository.findById(user.getId());
        if (existedUser.isPresent()) {
            BeanUtils.copyNonNullProperties(user, existedUser.get());
            userRepository.save(existedUser.get());
            return ResponseEntity.ok(MessageFormat.format("User with ID {0} updated", user.getId()));
        } else {
           return ResponseEntity.ok(MessageFormat.format("User with ID {0} not found", user.getId()));
        }
    }

    @Override
    public void dellAll() {
        userRepository.deleteAll();
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.ok(MessageFormat.format("User with ID {0} not found", id));
        } else {
            userRepository.deleteById(id);
            return ResponseEntity.ok(MessageFormat.format("User with ID {0}  deleted", id));
        }


    }
}
//.orElseThrow(() ->
//                new EntityNo
