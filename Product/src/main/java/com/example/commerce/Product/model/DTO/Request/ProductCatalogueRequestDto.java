package com.example.commerce.Product.model.DTO.Request;

import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.ProductSpecs;
import com.example.commerce.Product.utils.enums.ProductColor;
import com.example.commerce.Product.utils.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class ProductCatalogueRequestDto {

    @NonNull
    private String productName;

    private String productDescription;

    @NonNull
    private ProductColor productColor;

    private Brand associatedBrand;

    @NonNull
    private ProductStatus productStatus;

    private List<ProductSpecs> specsList;

    private float maximumRetailPrice;

    private float sellingPrice;

    private float discount;
}
