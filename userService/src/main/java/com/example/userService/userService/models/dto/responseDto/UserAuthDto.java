package com.example.userService.userService.models.dto.responseDto;

import java.util.Set;
import lombok.Data;

@Data
public class UserAuthDto {
    private String emailId;
    private Set<String> roles;
}
