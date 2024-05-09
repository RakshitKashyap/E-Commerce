package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.CategoryRequestDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.Category;
import com.example.commerce.Product.repository.CategoryAssociationRepository;
import com.example.commerce.Product.repository.CategoryRepository;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        return null;
    }

    private CategoryResponseDto convertToResponse(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setCategoryName(category.getCategoryName());
        responseDto.setDescription(category.getDescription());
        responseDto.setCreatedOn(category.getCreatedOn());
        responseDto.setCreatedBy(category.getCreatedBy());
        responseDto.setModifiedOn(category.getModifiedOn());
        responseDto.setModifiedBy(category.getModifiedBy());
        return responseDto;
    }
}
