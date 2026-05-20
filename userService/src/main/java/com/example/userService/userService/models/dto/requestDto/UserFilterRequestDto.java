package com.example.userService.userService.models.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record UserFilterRequestDto (

    String email,
    String mobile,
    String firstName,
    String lastname
){}
