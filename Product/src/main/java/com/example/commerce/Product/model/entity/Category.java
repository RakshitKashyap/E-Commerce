package com.example.commerce.Product.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class Category extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(unique = true)
    private String categoryUUID;

    @Column(unique = true)
    private String categoryName;

    private String description;

}
