package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.model.DTO.Response.AssociateEntitiesResponse;
import com.example.commerce.Product.model.DTO.Response.AssociateResponseDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.CategoryAssociations;
import com.example.commerce.Product.repository.CategoryAssociationRepository;
import com.example.commerce.Product.service.CategoryAssociationService;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<CategoryAssociations> categoryAssociations = new ArrayList<>();

        for (AddAssociateRequestDTO requestDTO : requestListDto) {
            CategoryAssociations associations = new CategoryAssociations();
            associations.setAssociationUUID(UUID.randomUUID().toString());
            associations.setMainCategory(categoryId);
            associations.setAssociatedEntityId(requestDTO.getAssociatedEntityId());
            associations.setRelation(requestDTO.getRelations());
            associations.setStatus(true);
            categoryAssociations.add(associations);
        }
        categoryAssociations =  associationRepository.saveAll(categoryAssociations);
        
        return convertToAssociateResponse(categoryAssociations, categoryResponse);
    }

    private AssociateResponseDto convertToAssociateResponse(List<CategoryAssociations> associates, CategoryResponseDto category) {

        AssociateResponseDto responseDto = new AssociateResponseDto();
        responseDto.setCategoryId(category.getId());
        responseDto.setCategoryName(category.getCategoryName());
        List<AssociateEntitiesResponse> associateEntitiesResponses = new ArrayList<>();
        for (CategoryAssociations associations: associates){
            AssociateEntitiesResponse response = new AssociateEntitiesResponse();
            response.setEntityId(associations.getId());
            response.setEntityName(getNameOfEntity(associations.getAssociatedEntityId(), associations.getRelation()));
            response.setRelations(associations.getRelation());
            associateEntitiesResponses.add(response);
        }
        responseDto.setAssociatedEntity(associateEntitiesResponses);
        return responseDto;
    }

    private String getNameOfEntity(long associatedEntityId, CategoryRelations relation) {
        if (relation==CategoryRelations.CHILD || relation==CategoryRelations.PARENT){
            CategoryResponseDto category = categoryService.viewCategoryById(associatedEntityId);
            return category.getCategoryName();
        }
        else {
            return "";
        }
    }
}
