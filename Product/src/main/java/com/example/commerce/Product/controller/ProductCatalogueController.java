package com.example.commerce.Product.controller;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.FilterRequestDto;
import com.example.commerce.Product.model.DTO.Request.ProductCatalogueRequestDto;
import com.example.commerce.Product.model.DTO.Response.ProductCatalogueResponseDto;
import com.example.commerce.Product.service.ProductCatalogueService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products/v1")
@Slf4j
public class ProductCatalogueController {

    private final ProductCatalogueService catalogueService;

    public ProductCatalogueController(ProductCatalogueService service) {
        catalogueService = service;
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<ProductCatalogueResponseDto>> getAllProductsAvailable(){
        log.info("initiating endpoint to fetch all available Products");
        return new ResponseEntity(catalogueService.getAllAvailableProducts(), HttpStatus.OK);
    }

    @GetMapping("/view/brand/{brandId}")
    public ResponseEntity getAllProductsByBrand(@PathVariable(name = "brandId")String brandId){
        log.info("initiating endpoint to get all Available Products for a brand :: {}", brandId);
        if(brandId.trim().isEmpty() || Objects.isNull(brandId)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        List<ProductCatalogueResponseDto> catalogueResponseDtoList = catalogueService.getAllProductsByBrand(brandId);
        if( Objects.isNull(catalogueResponseDtoList) || catalogueResponseDtoList.isEmpty()){
            return new ResponseEntity(Collections.EMPTY_LIST, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(catalogueResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/view/category/{categoryId}")
    public ResponseEntity getAvailableProductByCategory(@PathVariable(name = "categoryId")String categoryId){
        log.info("initiating endpoint to get all Available Products for a Category :: {}", categoryId);
        if(categoryId.trim().isEmpty() || Objects.isNull(categoryId)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        List<ProductCatalogueResponseDto> catalogueResponseDtoList = catalogueService.getAllProductsByCategory(categoryId);
        if( Objects.isNull(catalogueResponseDtoList) || catalogueResponseDtoList.isEmpty()){
            return new ResponseEntity(Collections.EMPTY_LIST, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(catalogueResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/view/filter")
    public ResponseEntity getProductViaFilter(@RequestBody FilterRequestDto requestDto){
        log.info("initiating endpoint to get all Available Products for a Filter Request body :: {}", requestDto);
        if(Objects.isNull(requestDto)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        List<ProductCatalogueResponseDto> catalogueResponseDtoList = catalogueService.getAllProductsByFiltering(requestDto);
        if( Objects.isNull(catalogueResponseDtoList) || catalogueResponseDtoList.isEmpty()){
            return new ResponseEntity(Collections.EMPTY_LIST, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(catalogueResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/view/bestDeals")
    public ResponseEntity getBestDealsProducts(){
        /**
         * Defining best deals as Product with 70%+ discount
         */

        log.info("initiating endpoint to get all Available Products for a best Deals");
        List<ProductCatalogueResponseDto> catalogueResponseDtoList = catalogueService.getAllProductsBestDeals();
        if( Objects.isNull(catalogueResponseDtoList) || catalogueResponseDtoList.isEmpty()){
            return new ResponseEntity(Collections.EMPTY_LIST, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(catalogueResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addNewProduct(@RequestBody ProductCatalogueRequestDto requestDto){
        log.info("initiating endpoint to add new Products with request :: {}", requestDto);
        if(Objects.isNull(requestDto)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        ProductCatalogueResponseDto responseDto = catalogueService.addNewProduct(requestDto);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(@PathVariable(name = "productId") String productId, @RequestBody ProductCatalogueRequestDto requestDto){
        log.info("initiating endpoint to update Products with request :: {}", requestDto);
        if(Objects.isNull(requestDto)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        ProductCatalogueResponseDto responseDto = catalogueService.updateProductCatalogue(productId, requestDto);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity removeProduct(@PathVariable(name = "productId")String productId){
        log.info("initiating endpoint to remove Products with Id :: {}", productId);
        if(Objects.isNull(productId) || productId.trim().isEmpty()){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        catalogueService.removeProductCatalogue(productId);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
