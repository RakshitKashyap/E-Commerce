package com.example.userService.userService.controller;

import com.example.userService.userService.models.dto.responseDto.ResponseData;
import org.springframework.web.bind.annotation.*;

@RequestMapping(BaseController.V1+"/user")
public interface UserProfileController {

    @GetMapping("/me")
    ResponseData getCurrentUserDetails();

    @PostMapping("/profile")
    ResponseData updatePersonalDetails(@RequestBody Object request);

    @GetMapping("/profile/{userId}")
    ResponseData fetchPersonalDetailsByUserId(@PathVariable(name = "userId") String userId);

    @PostMapping("/profile/{userId}")
    ResponseData updatePersonalDetailsByUserId(@PathVariable(name = "userId") String userId,@RequestBody Object request);

    @PostMapping("/change-password")
    ResponseData updatePassword(@RequestBody Object request);

    @GetMapping("/logs")
    ResponseData fetchLastTenLoginLog();

}
