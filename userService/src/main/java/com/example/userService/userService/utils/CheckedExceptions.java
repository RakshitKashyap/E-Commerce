package com.example.userService.userService.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CheckedExceptions {

    USER_NOT_EXIST(404, "User not Exist"),
    TOKEN_EXPIRED(403, "Token Expired"),
    NO_CONTENT_AVAILABLE(204, "NO Content Available"),
    INVALID_INPUT(500, "Invalid Input"),
    INVALID_REQUEST(400, "Invalid Request");

    private int errorCode;
    private String message;
}
