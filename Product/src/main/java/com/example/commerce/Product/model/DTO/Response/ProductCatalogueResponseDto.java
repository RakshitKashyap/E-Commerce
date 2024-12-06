package com.example.commerce.Product.model.DTO.Response;

import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.ProductCatalogue;
import com.example.commerce.Product.model.entity.ProductSpecs;
import com.example.commerce.Product.utils.enums.ProductColor;
import com.example.commerce.Product.utils.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
@NoArgsConstructor
public class ProductCatalogueResponseDto extends ProductCatalogue {

    private Long productId;

    private String productUUID;

    private String productName;

    private String productDescription;

    private ProductColor productColor;

    private ProductStatus productStatus;

    private Brand associatedBrand;

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
