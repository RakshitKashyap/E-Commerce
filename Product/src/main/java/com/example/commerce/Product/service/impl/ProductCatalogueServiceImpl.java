package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.FilterRequestDto;
import com.example.commerce.Product.model.DTO.Request.ProductCatalogueRequestDto;
import com.example.commerce.Product.model.DTO.Response.BrandResponseDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.DTO.Response.ProductCatalogueResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.Category;
import com.example.commerce.Product.model.entity.CategoryAssociations;
import com.example.commerce.Product.model.entity.ProductCatalogue;
import com.example.commerce.Product.repository.ProductCatalogueRepository;
import com.example.commerce.Product.repository.ProductSpecsRepository;
import com.example.commerce.Product.service.BrandService;
import com.example.commerce.Product.service.CategoryAssociationService;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.service.ProductCatalogueService;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductCatalogueServiceImpl implements ProductCatalogueService {

    private final ProductCatalogueRepository catalogueRepository;

    private final ProductSpecsRepository productSpecsRepository;

    private final CategoryAssociationService associationService;

    private final BrandService brandService;

    private final CategoryService categoryService;

    public ProductCatalogueServiceImpl(ProductCatalogueRepository catalogueRepository, ProductSpecsRepository productSpecsRepository, CategoryAssociationService associationService, BrandService brandService, CategoryService categoryService) {
        this.catalogueRepository = catalogueRepository;
        this.productSpecsRepository = productSpecsRepository;
        this.associationService = associationService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductCatalogueResponseDto> getAllAvailableProducts() {
        List<ProductCatalogue> productCatalogues = catalogueRepository.findAll().stream().filter(product->product.isStatus()).collect(Collectors.toList());
        if(productCatalogues.isEmpty()|| Objects.isNull(productCatalogues)){
            throw new CustomExceptions(CheckedExceptions.NO_CONTENT_AVAILABLE);
        }

        return productCatalogues.stream().map(productCatalogue -> {
            ProductCatalogueResponseDto responseDto = new ProductCatalogueResponseDto();
            BeanUtils.copyProperties(productCatalogue,responseDto);
            return responseDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductCatalogueResponseDto> getAllProductsByBrand(String brandId) {

        BrandResponseDto brandResponseDto = brandService.getBrandById(brandId);
        if(Objects.isNull(brandResponseDto)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        List<ProductCatalogue> productCatalogueList = catalogueRepository.findByAssociatedBrandAndStatus(brandResponseDto.getId(),true);

        return productCatalogueList.stream().map(productCatalogue -> {
            ProductCatalogueResponseDto responseDto = new ProductCatalogueResponseDto();
            BeanUtils.copyProperties(productCatalogue,responseDto);
            return responseDto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<ProductCatalogueResponseDto> getAllProductsByCategory(String categoryId) {
        CategoryResponseDto categoryResponseDto = categoryService.viewCategoryById(Long.parseLong(categoryId));

        if(Objects.isNull(categoryResponseDto)){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }

        List<Long> productList = associationService.findProductsByRelationAndMainCategory(CategoryRelations.PRODUCT, categoryId);


        List<ProductCatalogue> productCatalogueList = catalogueRepository.findAll().stream()
                .filter(productCatalogue ->     productList.contains(productCatalogue.getProductId()))
                .collect(Collectors.toList());

        return productCatalogueList.stream().map(productCatalogue -> {
            ProductCatalogueResponseDto responseDto = new ProductCatalogueResponseDto();
            BeanUtils.copyProperties(productCatalogue,responseDto);
            return responseDto;
        }).collect(Collectors.toList());


    }

    @Override
    public List<ProductCatalogueResponseDto> getAllProductsByFiltering(FilterRequestDto requestDto) {

        List<ProductCatalogue> productCatalogueList = catalogueRepository.findAll();

    return productCatalogueList.stream()
        .filter(
            productCatalogue ->
                (requestDto
                        .getBrandList()
                        .contains(productCatalogue.getAssociatedBrand())) // on the basis of brand
                    && requestDto
                        .getCategoryList() // on the basis of category associated
                        .contains(getCategoryAssociated(productCatalogue))
                    && requestDto
                        .getColorList()
                        .contains(productCatalogue.getProductColor()) // on the basis of color
                    && (productCatalogue.getDiscount()
                        == requestDto.getLeastDiscount()) // on the basis of discount
                        // on the basis of price range
                    && (productCatalogue.getSellingPrice() >= requestDto.getMinimumPriceRange() ||
                        productCatalogue.getSellingPrice() <= requestDto.getMaximumPriceRange()))
        .map(
            productCatalogue -> {
              ProductCatalogueResponseDto responseDto = new ProductCatalogueResponseDto();
              BeanUtils.copyProperties(productCatalogue, responseDto);
              return responseDto;
            })
        .collect(Collectors.toList());
    }

    private List<CategoryResponseDto> getCategoryAssociated(ProductCatalogue productCatalogue) {
        List<CategoryAssociations> associations = categoryService.getAssociationByEntityAndRelation(productCatalogue.getProductId(), CategoryRelations.PRODUCT);

        return categoryService.viewAllCategories().stream()
                .filter(categoryResponseDto -> associations.contains(categoryResponseDto.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductCatalogueResponseDto> getAllProductsBestDeals() {
        //  Defining best deals as Product with 70%+ discount
        return catalogueRepository.findAll().stream().filter(product -> (product.getDiscount()>=70.00)).map(
                        productCatalogue -> {
                            ProductCatalogueResponseDto responseDto = new ProductCatalogueResponseDto();
                            BeanUtils.copyProperties(productCatalogue, responseDto);
                            return responseDto;
                        })
                .collect(Collectors.toList());
    }

    @Override
    public ProductCatalogueResponseDto addNewProduct(ProductCatalogueRequestDto requestDto) {

        /**
         * validating  only brand at the moment iff available
         */

        ProductCatalogue catalogue = new ProductCatalogue();

        catalogue.setProductUUID(UUID.randomUUID().toString());
        catalogue.setProductName(requestDto.getProductName());
        catalogue.setProductDescription(requestDto.getProductDescription());
        catalogue.setProductColor(requestDto.getProductColor());
        catalogue.setProductStatus(requestDto.getProductStatus());

        if(Objects.nonNull(requestDto.getAssociatedBrand())){

        }
        catalogue.setSpecsList(requestDto.getSpecsList());
        catalogue.setDiscount(requestDto.getDiscount());
        catalogue.setMaximumRetailPrice(requestDto.getMaximumRetailPrice());
        catalogue.setSellingPrice(requestDto.getSellingPrice());
        catalogue = catalogueRepository.save(catalogue);
        ProductCatalogueResponseDto responseDto = new ProductCatalogueResponseDto();
        BeanUtils.copyProperties(catalogue, responseDto);

        return responseDto;
    }

    @Override
    public ProductCatalogueResponseDto updateProductCatalogue(String productId, ProductCatalogueRequestDto requestDto) {
        Optional<ProductCatalogue> optionalProductCatalogue = catalogueRepository.findById(Long.parseLong(productId));
        if(optionalProductCatalogue.isEmpty()){
            return null;
        }
        ProductCatalogue catalogue = optionalProductCatalogue.get();
        catalogue.setProductName(requestDto.getProductName());
        catalogue.setProductDescription(requestDto.getProductDescription());
        catalogue.setProductColor(requestDto.getProductColor());
        catalogue.setAssociatedBrand(requestDto.getAssociatedBrand());
        catalogue.setProductStatus(requestDto.getProductStatus());
        catalogue.setMaximumRetailPrice(requestDto.getMaximumRetailPrice());
        catalogue.setDiscount(requestDto.getDiscount());
        catalogue.setSpecsList(requestDto.getSpecsList());

        catalogue = catalogueRepository.save(catalogue);

        ProductCatalogueResponseDto responseDto = new ProductCatalogueResponseDto();
        BeanUtils.copyProperties(catalogue, responseDto);

        return responseDto;
    }

    @Override
    public boolean removeProductCatalogue(String productId) {
        Optional<ProductCatalogue> optionalProductCatalogue = catalogueRepository.findById(Long.parseLong(productId));
        if(optionalProductCatalogue.isEmpty()){
            return false;
        }
        ProductCatalogue catalogue = optionalProductCatalogue.get();
        catalogue.setStatus(false);
        catalogue = catalogueRepository.save(catalogue);
        return true;
    }
}
