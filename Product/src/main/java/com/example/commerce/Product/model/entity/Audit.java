package com.example.commerce.Product.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class Audit {

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime modifiedOn;

    private String modifiedBy;

    private boolean status;
}
