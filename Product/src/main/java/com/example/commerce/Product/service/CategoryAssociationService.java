package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.model.DTO.Response.AssociateResponseDto;

import java.util.List;

public interface CategoryAssociationService {
    AssociateResponseDto addCategoryAssociation(Long categoryId, List<AddAssociateRequestDTO> requestListDto);
}
