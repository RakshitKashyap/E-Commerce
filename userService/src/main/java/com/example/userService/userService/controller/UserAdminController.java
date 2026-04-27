package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.responseDto.ResponseData;
import org.springframework.web.bind.annotation.*;

@RequestMapping(BaseController.V2+"/admin/users")
public interface UserAdminController {

    @GetMapping("/")
    ResponseData fetchAllUser();

    @GetMapping("/by-filter")
    ResponseData fetchAllUserByFilter();

    @DeleteMapping("/{userId}/disable")
    ResponseData disableUser(@PathVariable(name = "userId") String userId);

    @PostMapping("/{userId}/update-roles")
    ResponseData updateRoleForUser(@PathVariable(name = "userId") String userId);

}