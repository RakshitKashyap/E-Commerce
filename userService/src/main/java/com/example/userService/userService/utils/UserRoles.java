package com.example.userService.userService.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
public enum UserRoles {

    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN"),
    VENDOR("VENDOR"),
    CONTRACTOR("CONTRACTOR");

    private String roleDescription;

}
