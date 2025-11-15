package com.example.commerce.Product.model.DTO.Response;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class CategoryResponseDto {

    private Long id;

    private String CategoryName;

    private String description;

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime modifiedOn;

    private String modifiedBy;

}
