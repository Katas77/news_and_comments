package com.example.NewsAndComments.web.mapper;


import com.example.NewsAndComments.model.User;
import com.example.NewsAndComments.web.dto.user.UpsertUserRequest;
import com.example.NewsAndComments.web.dto.user.UserListResponse;
import com.example.NewsAndComments.web.dto.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class})
public interface UserMapper {
    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    default UserListResponse userListResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUserResponses(users.stream().map(this::userToResponse).collect(Collectors.toList()));
        return response;
    }

}
