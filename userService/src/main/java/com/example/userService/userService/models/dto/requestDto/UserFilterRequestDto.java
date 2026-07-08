package com.example.userService.userService.models.dto.requestDto;
public record UserFilterRequestDto (

    String email,
    String mobile,
    String firstName,
    String lastname
){}
