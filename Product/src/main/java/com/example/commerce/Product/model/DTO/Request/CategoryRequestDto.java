package com.example.commerce.Product.model.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@Getter
@Validated
public class CategoryRequestDto {

    @NonNull
    private String CategoryName;

    private String description;
}
