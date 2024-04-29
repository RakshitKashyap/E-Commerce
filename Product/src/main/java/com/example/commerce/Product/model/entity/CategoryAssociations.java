package com.example.commerce.Product.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CategoryAssociations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String associationUUID;

    private long mainCategory;

    private long associatedEntity;

    @Enumerated(value = EnumType.STRING)
    private CategoryRelations relation;

    private boolean status;

}


@AllArgsConstructor
@Getter
enum CategoryRelations{
    CHILD, PARENT, PRODUCT;
}