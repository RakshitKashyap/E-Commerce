package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.CategoryRequestDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.Category;
import com.example.commerce.Product.model.entity.CategoryAssociations;
import com.example.commerce.Product.repository.CategoryAssociationRepository;
import com.example.commerce.Product.repository.CategoryRepository;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryAssociationRepository associationRepository;

    @Override
    public List<CategoryResponseDto> viewAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty() || Objects.isNull(categories)){
            log.info("No categories available at the moment");
            throw new CustomExceptions(CheckedExceptions.NO_CONTENT_AVAILABLE);
        }
        
        return categories.stream().map(element -> convertToResponse(element)).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto viewCategoryById(Long categoryId) {

        if (categoryId == null){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }

        return convertToResponse(category.get());
    }

    @Override
    public CategoryResponseDto addNewCategory(CategoryRequestDto requestDto) {

        Category category = new Category();

        category.setCategoryUUID(UUID.randomUUID().toString());
        category.setCategoryName(requestDto.getCategoryName());
        category.setDescription(requestDto.getDescription());
        category.setCreatedOn(LocalDateTime.now());
        category.setCreatedBy("user");
        category.setModifiedOn(LocalDateTime.now());
        category.setModifiedBy("user");
        category.setStatus(true);

        category = categoryRepository.save(category);
        return convertToResponse(category);
    }

    @Override
    public List<CategoryAssociations> getAssociationByEntityAndRelation(Long productId, CategoryRelations relation) {

        List<CategoryAssociations> associations = associationRepository.findByRelationAndMainCategory(relation, productId);

        if(associations.isEmpty() || Objects.isNull(associations)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        return null;
    }

    private CategoryResponseDto convertToResponse(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getCategoryId());
        responseDto.setCategoryName(category.getCategoryName());
        responseDto.setDescription(category.getDescription());
        responseDto.setCreatedOn(category.getCreatedOn());
        responseDto.setCreatedBy(category.getCreatedBy());
        responseDto.setModifiedOn(category.getModifiedOn());
        responseDto.setModifiedBy(category.getModifiedBy());
        return responseDto;
    }
}
