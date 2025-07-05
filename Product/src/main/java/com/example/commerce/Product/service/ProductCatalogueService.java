package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Request.FilterRequestDto;
import com.example.commerce.Product.model.DTO.Request.ProductCatalogueRequestDto;
import com.example.commerce.Product.model.DTO.Response.ProductCatalogueResponseDto;

import java.util.List;

public interface ProductCatalogueService {
    List<ProductCatalogueResponseDto> getAllAvailableProducts();

    List<ProductCatalogueResponseDto> getAllProductsByBrand(String brandId);

    List<ProductCatalogueResponseDto> getAllProductsByCategory(String categoryId);

    List<ProductCatalogueResponseDto> getAllProductsByFiltering(FilterRequestDto requestDto);

    List<ProductCatalogueResponseDto> getAllProductsBestDeals();

    ProductCatalogueResponseDto addNewProduct(ProductCatalogueRequestDto requestDto);

    ProductCatalogueResponseDto updateProductCatalogue(String productId, ProductCatalogueRequestDto requestDto);

    boolean removeProductCatalogue(String productId);
}
