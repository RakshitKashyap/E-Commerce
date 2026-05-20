package com.example.userService.userService.service;

import com.example.userService.userService.models.dto.requestDto.AuthenticationRequestDto;
import com.example.userService.userService.models.dto.requestDto.UserFilterRequestDto;
import com.example.userService.userService.models.dto.requestDto.UserRegistrationRequestDto;
import com.example.userService.userService.utils.UserRoles;

import java.util.List;

public interface UserService {
    Object registerNewUser(UserRegistrationRequestDto requestDto);

    Object resetPassword(String email);

    Object fetchAllUsers();

    Object fetchUserByFilter(UserFilterRequestDto filterRequest);

    Object disableUser(String userId);

    Object updateUserRole(String userId, List<UserRoles> roles);
}
