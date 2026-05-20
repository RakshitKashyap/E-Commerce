package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.requestDto.UserFilterRequestDto;
import com.example.userService.userService.models.dto.responseDto.ResponseData;
import com.example.userService.userService.service.UserService;
import com.example.userService.userService.utils.UserRoles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAdminImplController implements UserAdminController {

  private final UserService userService;

  @Override
  public ResponseData fetchAllUser() {
    log.info("initiating endpoint for fetching all users");

    return new ResponseData(200, userService.fetchAllUsers());
  }

  @Override
  public ResponseData fetchAllUserByFilter(UserFilterRequestDto filterRequest) {
    log.info("initiating endpoint for fetching all users by filter");
    return new ResponseData(200, userService.fetchUserByFilter(filterRequest));
  }

  @Override
  public ResponseData disableUser(String userId) {
    log.info("initiating endpoint to deactivate User");
    return new ResponseData(200, userService.disableUser(userId));
  }

  @Override
  public ResponseData updateRoleForUser(String userId, List<UserRoles> roles) {
      log.info("initiating endpoint to update roles for User");
      return new ResponseData(200, userService.updateUserRole(userId, roles));
  }

}