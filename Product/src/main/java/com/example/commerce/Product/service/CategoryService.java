package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Request.CategoryRequestDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> viewAllCategories();

    CategoryResponseDto viewCategoryById(Long categoryId);

    CategoryResponseDto addNewCategory(CategoryRequestDto requestDto);
}
