package com.example.commerce.Product.model.DTO.Response;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
public class BrandResponseDto {

    private Long id;

    private String brandName;

    private String brandDescription;

    private boolean availableStatus;

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime modifiedOn;

     private String modifiedBy;
}
