package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Request.BrandRequestDto;
import com.example.commerce.Product.model.DTO.Response.BrandResponseDto;
import com.example.commerce.Product.model.entity.Brand;

import java.util.List;

public interface BrandService {
    List<BrandResponseDto> getAll();

    BrandResponseDto getBrandDtoById(String brandId);

    List<BrandResponseDto> getByCategoryAssociation(String categoryId);

    List<BrandResponseDto> getBestDeals(String discount);

    BrandResponseDto addNewBrand(BrandRequestDto requestDto);

    BrandResponseDto updateBrand(Long brandId, BrandRequestDto requestDto);

    boolean removeBrand(Long brandId);

    Brand getBrandById(String brandId);
}
