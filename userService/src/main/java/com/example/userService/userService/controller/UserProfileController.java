package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.responseDto.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(BaseController.V1+"/user")
public interface UserProfileController {

    @GetMapping("/me")
    ResponseData getCurrentUserDetails();

    @PostMapping("/profile")
    ResponseData updatePersonalDetails(Object request);

    @GetMapping("/profile/{userId}")
    ResponseData fetchPersonalDetailsByUserId(@PathVariable(name = "userId") String userId);

    @PostMapping("/profile/{userId}")
    ResponseData updatePersonalDetailsByUserId(@PathVariable(name = "userId") String userId, Object request);

    @PostMapping("/change-password")
    ResponseData updatePassword(Object request);

    @GetMapping("/logs")
    ResponseData fetchLastTenLoginLog();

}
