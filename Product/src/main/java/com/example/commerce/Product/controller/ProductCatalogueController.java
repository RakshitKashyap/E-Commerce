package com.example.commerce.Product.controller;

import com.example.commerce.Product.service.ProductCatalogueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/view/category/{categoryId}")
    public ResponseEntity getAvailableProductByCategory(){return null;}

    @PostMapping("/view/filter")
    public ResponseEntity getProductViaFilter(){return null;}

    @GetMapping("/view/bestDeals")
    public ResponseEntity getBestDealsProducts(){return null;}

    @PostMapping("/add")
    public ResponseEntity addNewProduct(){return  null;}

    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(){return null;}

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity removeProduct(){return null;}
}
