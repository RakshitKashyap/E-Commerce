package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.requestDto.AuthenticationRequestDto;
import com.example.userService.userService.models.dto.requestDto.UserRegistrationRequestDto;
import com.example.userService.userService.models.dto.responseDto.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(BaseController.V1+"/user")
public interface UserController {

    @PostMapping("/register")
    ResponseData registerNewUser(@RequestBody UserRegistrationRequestDto requestDto);

    @PostMapping("/login")
    ResponseData loginUser(@RequestBody AuthenticationRequestDto requestDto);

    @PostMapping("/forget-password")
    ResponseData forgetPassword(@RequestBody Object request);

}
