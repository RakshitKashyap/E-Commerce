package com.example.commerce.Product.model.DTO.Request;

import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.ProductSpecs;
import com.example.commerce.Product.utils.enums.ProductColor;
import com.example.commerce.Product.utils.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class ProductCatalogueRequestDto {

    @NonNull
    private String productName;

    private String productDescription;

    @NonNull
    private Long categoryId;

    @NonNull
    private ProductColor productColor;

    @NonNull
    private String associatedBrand;

    @NonNull
    private ProductStatus productStatus;

    private List<ProductSpecRequestDto> specsList;

    private float maximumRetailPrice;

    private float sellingPrice;
}
