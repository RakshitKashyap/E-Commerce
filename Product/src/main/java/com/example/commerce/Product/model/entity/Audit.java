package com.example.commerce.Product.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Audit {

    @CreatedDate
    private LocalDateTime createdOn;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime modifiedOn;

    @LastModifiedBy
    private String modifiedBy;

    private boolean status;
}
