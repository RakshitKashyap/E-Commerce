package com.example.commerce.Product.model.entity;

import com.example.commerce.Product.utils.enums.ProductColor;
import com.example.commerce.Product.utils.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table
@Data
public class ProductCatalogue extends Audit {

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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand associatedBrand;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<ProductSpecs> specsList;

    private float maximumRetailPrice;

    private float discount;

    private float sellingPrice;

}
