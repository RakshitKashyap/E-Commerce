package com.example.commerce.Product.controller;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
