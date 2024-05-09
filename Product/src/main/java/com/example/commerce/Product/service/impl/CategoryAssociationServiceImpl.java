package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.model.DTO.Response.AssociateResponseDto;
import com.example.commerce.Product.repository.CategoryAssociationRepository;
import com.example.commerce.Product.service.CategoryAssociationService;
import com.example.commerce.Product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryAssociationServiceImpl implements CategoryAssociationService {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryAssociationRepository associationRepository;


    @Override
    public AssociateResponseDto addCategoryAssociation(Long categoryId, List<AddAssociateRequestDTO> requestListDto) {
        return null;
    }
}
