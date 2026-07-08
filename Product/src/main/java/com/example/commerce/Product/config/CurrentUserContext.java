package com.example.commerce.Product.config;

import com.example.commerce.Product.model.DTO.Response.UserAuthDto;

public class CurrentUserContext {
    private static final ThreadLocal<UserAuthDto> CURRENT_USER = new ThreadLocal<>();

    public static void set(UserAuthDto user) {
        CURRENT_USER.set(user);
    }

    public static UserAuthDto get() {
        return CURRENT_USER.get();
    }

    public static String getEmail() {
        UserAuthDto user = CURRENT_USER.get();
        return user == null ? "system" : user.getEmailId();
    }

    public static void clear() {
        CURRENT_USER.remove();
    }
}
