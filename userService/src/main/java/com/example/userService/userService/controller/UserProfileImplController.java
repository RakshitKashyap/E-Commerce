package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.responseDto.ResponseData;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileImplController implements UserProfileController {

    @Override
    public ResponseData getCurrentUserDetails() {
        return null;
    }

    @Override
    public ResponseData updatePersonalDetails(Object request) {
        return null;
    }

    @Override
    public ResponseData fetchPersonalDetailsByUserId(String userId) {
        return null;
    }

    @Override
    public ResponseData updatePersonalDetailsByUserId(String userId, Object request) {
        return null;
    }

    @Override
    public ResponseData updatePassword(Object request) {
        return null;
    }

    @Override
    public ResponseData fetchLastTenLoginLog() {
        return null;
    }
}
