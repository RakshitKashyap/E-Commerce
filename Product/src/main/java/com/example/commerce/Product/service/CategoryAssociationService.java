package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.model.DTO.Response.AssociateResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.utils.enums.CategoryRelations;

import java.util.List;

public interface CategoryAssociationService {
    AssociateResponseDto addCategoryAssociation(Long categoryId, List<AddAssociateRequestDTO> requestListDto);

    AssociateResponseDto addCategoryAssociationToBrands(String categoryId, List<Brand> brands);

    List<Long> findProductsByRelationAndMainCategory(CategoryRelations product, String categoryId);
}
