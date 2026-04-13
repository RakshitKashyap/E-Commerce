package com.example.commerce.Product.model.DTO.Response;

import com.example.commerce.Product.model.DTO.mini.BrandMiniResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.ProductCatalogue;
import com.example.commerce.Product.model.entity.ProductSpecs;
import com.example.commerce.Product.utils.enums.ProductColor;
import com.example.commerce.Product.utils.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProductCatalogueResponseDto {

    private Long productId;

    private String productName;

    private String productDescription;

    private ProductColor productColor;

    private ProductStatus productStatus;

    private BrandMiniResponseDto associatedBrand;

    private List<ProductSpecResponseDto> specsList;

    private float maximumRetailPrice;

    private float discount;

    private float sellingPrice;

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime modifiedOn;

    private String modifiedBy;
}
