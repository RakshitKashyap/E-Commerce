package com.example.commerce.Product.model.DTO.Response;

import lombok.Data;

import java.util.Set;

@Data
public class UserAuthDto {

    private String emailId;
    private Set<String> roles;

}
