package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Request.BrandRequestDto;
import com.example.commerce.Product.model.DTO.Response.BrandResponseDto;
import com.example.commerce.Product.model.entity.Brand;

import java.util.List;

public interface BrandService {
    List<BrandResponseDto> getAll();

    BrandResponseDto getBrandDtoById(String brandId);

    List<BrandResponseDto> getByCategoryAssociation(String categoryId);

    List<BrandResponseDto> getBestDeals(int i);

    BrandResponseDto addNewBrand(BrandRequestDto requestDto);

    BrandResponseDto updateBrand(String brandId, BrandRequestDto requestDto);

    boolean removeBrand(String brandId);

    Brand getBrandById(String brandId);
}
