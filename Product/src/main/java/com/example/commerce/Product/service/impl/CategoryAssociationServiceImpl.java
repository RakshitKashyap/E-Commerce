package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.model.DTO.Response.AssociateResponseDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.CategoryAssociations;
import com.example.commerce.Product.repository.CategoryAssociationRepository;
import com.example.commerce.Product.service.CategoryAssociationService;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class CategoryAssociationServiceImpl implements CategoryAssociationService {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryAssociationRepository associationRepository;


    @Override
    public AssociateResponseDto addCategoryAssociation(Long categoryId, List<AddAssociateRequestDTO> requestListDto) {

        // check for the category existence

        CategoryResponseDto categoryResponse = categoryService.viewCategoryById(categoryId);
        if(Objects.isNull(categoryResponse)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        CategoryAssociations associations = new CategoryAssociations();



        associations.setAssociationUUID(UUID.randomUUID().toString());
        associations.setMainCategory(categoryId);
        associations.setAssociatedEntity(requestListDto.get(0).getAssociatedEntity());
        associations.setRelation(requestListDto.get(0).getRelations());
        associations.setStatus(true);



        return null;
    }
}
