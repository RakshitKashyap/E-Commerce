package com.example.userService.userService.service;

import com.example.userService.userService.models.dto.requestDto.AuthenticationRequestDto;
import com.example.userService.userService.models.dto.requestDto.UserRegistrationRequestDto;

public interface UserService {
    Object registerNewUser(UserRegistrationRequestDto requestDto);

    Object userLogin(AuthenticationRequestDto requestDto);

    Object resetPassword(Object request);
}
