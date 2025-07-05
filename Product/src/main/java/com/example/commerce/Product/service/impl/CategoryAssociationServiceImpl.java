package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.model.DTO.Response.AssociateEntitiesResponse;
import com.example.commerce.Product.model.DTO.Response.AssociateResponseDto;
import com.example.commerce.Product.model.DTO.Response.BrandResponseDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.CategoryAssociations;
import com.example.commerce.Product.repository.CategoryAssociationRepository;
import com.example.commerce.Product.service.BrandService;
import com.example.commerce.Product.service.CategoryAssociationService;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryAssociationServiceImpl implements CategoryAssociationService {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
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

    @Override
    public AssociateResponseDto addCategoryAssociationToBrands(String categoryId, List<Brand> brands) {
        AssociateResponseDto responseDto = new AssociateResponseDto();
        CategoryResponseDto category = categoryService.viewCategoryById(Long.parseLong(categoryId));
        responseDto.setCategoryId(category.getId());
        responseDto.setCategoryName(category.getCategoryName());

        List<CategoryAssociations> categoryAssociations = new ArrayList<>();
        List<AssociateEntitiesResponse> associateEntitiesResponses = new ArrayList<>();

        for(Brand brand:brands){
            BrandResponseDto check = brandService.getBrandById(brand.getId().toString());
            if(Objects.isNull(check)){
                throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
            }
            CategoryAssociations associations = new CategoryAssociations();
            associations.setAssociationUUID(UUID.randomUUID().toString());
            associations.setAssociatedEntityId(brand.getId());
            associations.setMainCategory(category.getId());
            associations.setRelation(CategoryRelations.BRAND);
            associations.setStatus(true);
            categoryAssociations.add(associations);

            AssociateEntitiesResponse response = new AssociateEntitiesResponse();
            response.setEntityId(brand.getId());
            response.setEntityName(brand.getBrandName());
            response.setRelations(CategoryRelations.BRAND);
            associateEntitiesResponses.add(response);
        }
        associationRepository.saveAll(categoryAssociations);
        responseDto.setAssociatedEntity(associateEntitiesResponses);

        return responseDto;
    }

    @Override
    public List<Long> findProductsByRelationAndMainCategory(CategoryRelations product, String categoryId) {

        if(categoryId.trim().isEmpty()){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }

        return associationRepository
        .findByRelationAndMainCategory(product, Long.parseLong(categoryId))
        .stream()
        .mapToLong(entity -> entity.getAssociatedEntityId()).boxed().collect(Collectors.toList());

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
        if (relation==CategoryRelations.CHILD_CATEGORY || relation==CategoryRelations.PARENT_CATEGORY){
            CategoryResponseDto category = categoryService.viewCategoryById(associatedEntityId);
            return category.getCategoryName();
        }
        else {
            return "";
        }
    }
}
