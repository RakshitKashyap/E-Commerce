package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Request.BrandRequestDto;
import com.example.commerce.Product.model.DTO.Response.BrandResponseDto;

import java.util.List;

public interface BrandService {
    List<BrandResponseDto> getAll();

    BrandResponseDto getBrandById(String brandId);

    List<BrandResponseDto> getByCategoryAssociation(String categoryId);

    List<BrandResponseDto> getBestDeals(int i);

    BrandResponseDto addNewBrand(BrandRequestDto requestDto);

    BrandResponseDto updateBrand(String brandId, BrandRequestDto requestDto);

    boolean removeBrand(String brandId);
}
