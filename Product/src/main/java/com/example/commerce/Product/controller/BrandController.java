package com.example.commerce.Product.controller;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.BrandRequestDto;
import com.example.commerce.Product.model.DTO.Response.BrandResponseDto;
import com.example.commerce.Product.service.BrandService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/brand")
@Slf4j
public class BrandController {

    @Autowired
    private BrandService brandService;


    @GetMapping("/viewAll")
    public ResponseEntity getAllBrands(){
        log.info("initiating endpoint to GET all brands");
        List<BrandResponseDto> responseDtoList = brandService.getAll();
        if (responseDtoList.isEmpty()){
            throw new CustomExceptions(CheckedExceptions.NO_CONTENT_AVAILABLE);
        }
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/viewBy/{brandId}}")
    public ResponseEntity getBrandById(@PathVariable(name = "brandId")String brandId){
        log.info("initiating endpoint to GET brand by brandId: {} ", brandId);
        if(brandId.trim().isEmpty() || Objects.isNull(brandId.trim())){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        return new ResponseEntity(brandService.getBrandById(brandId), HttpStatus.OK);
    }

    @GetMapping("/viewBy/{categoryId}")
    public ResponseEntity getBrandsByCategory(@PathVariable(name = "categoryId")String categoryId){
        log.info("initiating endpoint to GET by category :: {}", categoryId);
        if(categoryId.trim().isEmpty() || Objects.isNull(categoryId.trim())){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        List<BrandResponseDto> responseDtoList = brandService.getByCategoryAssociation(categoryId);
        if (responseDtoList.isEmpty() || Objects.isNull(responseDtoList))
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/viewByBestDeals/")
    public ResponseEntity getBrandsByBestDeals(){
        log.info("initiating endpoint to GET Brands offering best Deals with 50% discount");
        List<BrandResponseDto> responseDtoList = brandService.getBestDeals(50);
        if (responseDtoList.isEmpty() || Objects.isNull(responseDtoList)){
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(responseDtoList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addNewBrand(@Validated @RequestBody BrandRequestDto requestDto){
        log.info("initiating endpoint to ADD/POST new Brand");
        BrandResponseDto responseDto = brandService.addNewBrand(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{brandId}")
    public ResponseEntity updateBrandDetails(@PathVariable(name = "brandId")String brandId, @RequestBody BrandRequestDto requestDto){
        log.info("initiating endpoint to UPDATE/PUT existing brand {}", brandId);
        BrandResponseDto responseDto = brandService.updateBrand(brandId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{brandId}")
    public ResponseEntity removeBrand(@PathVariable(name = "brandId")String brandId){
        log.info("initiating endpoint to REMOVE/DELETE Existing brand: {}", brandId);
        boolean result = brandService.removeBrand(brandId);
        return new ResponseEntity<>(((result)?"Brand Removed":" "), HttpStatus.OK);
    }

}
