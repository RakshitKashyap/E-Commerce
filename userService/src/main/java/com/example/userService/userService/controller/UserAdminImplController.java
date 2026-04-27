package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.responseDto.ResponseData;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAdminImplController implements UserAdminController{

    @Override
    public ResponseData fetchAllUser() {
        return null;
    }

    @Override
    public ResponseData fetchAllUserByFilter() {
        return null;
    }

    @Override
    public ResponseData disableUser(String userId) {
        return null;
    }

    @Override
    public ResponseData updateRoleForUser(String userId) {
        return null;
    }
}
