package com.example.commerce.Product.model.entity;

import com.example.commerce.Product.utils.enums.CategoryRelations;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class CategoryAssociations  extends Basic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String associationUUID;

    private long mainCategory;

    private long associatedEntityId;

    @Enumerated(value = EnumType.STRING)
    private CategoryRelations relation;

    private boolean status;

}



