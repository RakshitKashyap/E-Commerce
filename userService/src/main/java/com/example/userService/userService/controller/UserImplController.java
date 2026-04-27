package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.requestDto.AuthenticationRequestDto;
import com.example.userService.userService.models.dto.requestDto.UserRegistrationRequestDto;
import com.example.userService.userService.models.dto.responseDto.ResponseData;
import com.example.userService.userService.service.UserService;
import com.example.userService.userService.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserImplController implements UserController{

    private final UserService userService;

    private final AuthenticationService authenticationService;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserImplController.class);

    @Override
    public ResponseData registerNewUser(UserRegistrationRequestDto requestDto) {
        System.out.println("initiating endpoint to register New User"+ requestDto.toString());
        log.info("initiating endpoint to register New User {}", requestDto);
        return new ResponseData(201, userService.registerNewUser(requestDto));

    }

    @Override
    public ResponseData loginUser(AuthenticationRequestDto requestDto) {
        log.info("initiating endpoint to register New User");
        return new ResponseData(200, authenticationService.login(requestDto));
    }

    @Override
    public ResponseData forgetPassword(Object request) {
        log.info("initiating endpoint to register New User");
        return new ResponseData(200, userService.resetPassword(request));
    }
}
