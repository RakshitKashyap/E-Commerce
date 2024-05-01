package com.example.commerce.Product.controller;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.CategoryRequestDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("category/v1")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

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
    public ResponseEntity addNewCategory(@RequestBody CategoryRequestDto requestDto){
        if(Objects.isNull(requestDto)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        CategoryResponseDto responseDto = categoryService.addNewCategory(requestDto);
        if(Objects.isNull(responseDto)){
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

}
