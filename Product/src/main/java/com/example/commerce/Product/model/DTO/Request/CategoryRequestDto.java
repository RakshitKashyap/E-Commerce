package com.example.commerce.Product.model.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
public class CategoryRequestDto {

    @NonNull
    private String categoryName;

    private String description;
}
