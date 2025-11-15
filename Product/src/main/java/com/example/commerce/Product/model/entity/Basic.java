package com.example.commerce.Product.model.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class Basic {

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime modifiedOn;

    private String modifiedBy;

    private boolean status;
}
