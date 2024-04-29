package com.example.commerce.Product.controller;

import com.example.commerce.Product.service.ProductCatalogueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/v1")
@Slf4j
public class ProductCatalogueController {

    private final ProductCatalogueService catalogueService;

    public ProductCatalogueController(ProductCatalogueService service) {
        catalogueService = service;
    }

    @GetMapping("/viewAll")
    public ResponseEntity getAllProductsAvailable(){
        return null;
    }
}
