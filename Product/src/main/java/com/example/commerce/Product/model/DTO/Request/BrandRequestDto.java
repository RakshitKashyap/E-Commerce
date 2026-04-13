package com.example.commerce.Product.model.DTO.Request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class BrandRequestDto {

    @NonNull
    private String brandName;

    @NonNull
    private String brandDescription;
}
