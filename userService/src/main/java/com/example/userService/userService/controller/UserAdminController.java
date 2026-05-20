package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.requestDto.UserFilterRequestDto;
import com.example.userService.userService.models.dto.responseDto.ResponseData;
import com.example.userService.userService.utils.UserRoles;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(BaseController.V2+"/admin/users")
public interface UserAdminController {

    @GetMapping("/")
    ResponseData fetchAllUser();

    @GetMapping("/by-filter")
    ResponseData fetchAllUserByFilter( @RequestBody UserFilterRequestDto filterRequest);

    @DeleteMapping("/{userId}/disable")
    ResponseData disableUser(@PathVariable(name = "userId") String userId);

    @PostMapping("/{userId}/update-roles")
    ResponseData updateRoleForUser(@PathVariable(name = "userId") String userId, @RequestParam(name = "roles", required = true) List<UserRoles> roles);

}