package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Request.CategoryRequestDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.CategoryAssociations;
import com.example.commerce.Product.utils.enums.CategoryRelations;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> viewAllCategories();

    CategoryResponseDto viewCategoryById(Long categoryId);

    CategoryResponseDto addNewCategory(CategoryRequestDto requestDto);

    List<CategoryAssociations> getAssociationByEntityAndRelation(Long productId, CategoryRelations relation);
}
