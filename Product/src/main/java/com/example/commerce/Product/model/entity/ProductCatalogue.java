package com.example.commerce.Product.model.entity;

import com.example.commerce.Product.utils.enums.ProductColor;
import com.example.commerce.Product.utils.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Getter
@Setter
public class ProductCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productUUID;

    private String productName;

    private String productDescription;

    @Enumerated(value = EnumType.STRING)
    private ProductColor productColor;

    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;

    private List<ProductSpecs> specsList;

    private float maximumRetailPrice;

    private float discount;

    private float sellingPrice;

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime modifiedOn;

    private String modifiedBy;

    private boolean status;

}
