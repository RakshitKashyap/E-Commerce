package com.example.commerce.Product.controller;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.exceptions.GlobalExceptionHandler;
import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.model.DTO.Request.CategoryRequestDto;
import com.example.commerce.Product.model.DTO.Response.AssociateResponseDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.service.CategoryAssociationService;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    private final CategoryAssociationService associationService;

    @Autowired
    public CategoryController(CategoryAssociationService _categoryAssociation, CategoryService _categoryService) {
        this.associationService = _categoryAssociation;
        this.categoryService = _categoryService;
    }

    @GetMapping("/viewAll")
    public ResponseEntity viewAllCategories(){
        log.info("initiating api to view all categories..");
        List<CategoryResponseDto> responseDtoList = categoryService.viewAllCategories();
        if(responseDtoList.isEmpty() || Objects.isNull(responseDtoList)){
            return new ResponseEntity(responseDtoList, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity(responseDtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/view/{categoryId}")
    public ResponseEntity viewCategoryById(@PathVariable(name = "categoryId")Long categoryId){
        log.info("initiating api to get category by id :: {}", categoryId);
        CategoryResponseDto responseDto = categoryService.viewCategoryById(categoryId);
        if(Objects.isNull(responseDto)){
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(responseDto, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addNewCategory(@Validated@RequestBody CategoryRequestDto requestDto){
        if(Objects.isNull(requestDto)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        CategoryResponseDto responseDto = categoryService.addNewCategory(requestDto);
        if(Objects.isNull(responseDto)){
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/addAssociation/{categoryId}")
    public ResponseEntity addCategoryAssociate(@Validated @RequestBody List<AddAssociateRequestDTO> requestListDto, @PathVariable(name = "categoryId")Long categoryId){
        log.info("initiating api to add association to category for category Id: {}", categoryId);
        if(Objects.isNull(categoryId) || categoryId.toString().trim().isEmpty()){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }

        if(Objects.isNull(requestListDto) || requestListDto.isEmpty()){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        AssociateResponseDto responseDto = associationService.addCategoryAssociation(categoryId, requestListDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/{categoryId}/addBrands")
    public ResponseEntity addBrandToCategory(@PathVariable(name = "categoryId")String categoryId, List<Brand> brands){
        log.info("initiating api to add association to Brand for category Id: {}", categoryId);
        if(Objects.isNull(categoryId) || categoryId.toString().trim().isEmpty()){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        if(Objects.isNull(brands) || brands.isEmpty()){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        AssociateResponseDto responseDto = associationService.addCategoryAssociationToBrands(categoryId, brands);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }




}
