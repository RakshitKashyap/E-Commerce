package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.FilterRequestDto;
import com.example.commerce.Product.model.DTO.Request.ProductCatalogueRequestDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.DTO.Response.ProductCatalogueResponseDto;
import com.example.commerce.Product.model.DTO.Response.ProductSpecResponseDto;
import com.example.commerce.Product.model.DTO.mini.BrandMiniResponseDto;
import com.example.commerce.Product.model.entity.*;
import com.example.commerce.Product.repository.ProductCatalogueRepository;
import com.example.commerce.Product.repository.ProductSpecsRepository;
import com.example.commerce.Product.service.*;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCatalogueServiceImpl implements ProductCatalogueService {

    private final ProductCatalogueRepository catalogueRepository;
    private final ProductSpecService productSpecService;
    private final CategoryAssociationService associationService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    @Override
    public List<ProductCatalogueResponseDto> getAllAvailableProducts() {
        List<ProductCatalogue> productCatalogues = catalogueRepository.findAll().stream().filter(product->product.isStatus()).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(productCatalogues)){
            throw new CustomExceptions(CheckedExceptions.NO_CONTENT_AVAILABLE);
        }

        return productCatalogues.stream().map(productCatalogue -> transformToResponseDto(productCatalogue)).collect(Collectors.toList());
    }

    @Override
    public List<ProductCatalogueResponseDto> getAllProductsByBrand(String brandId) {

        Brand brand = brandService.getBrandById(brandId);
        if(Objects.isNull(brand)){
            throw new CustomExceptions(CheckedExceptions.INVALID_BRAND);
        }
        List<ProductCatalogue> productCatalogueList = catalogueRepository.findByAssociatedBrand(brand);

        return productCatalogueList.stream().map(productCatalogue -> transformToResponseDto(productCatalogue)).collect(Collectors.toList());
    }

    @Override
    public List<ProductCatalogueResponseDto> getAllProductsByCategory(String categoryId) {
        CategoryResponseDto categoryResponseDto = categoryService.viewCategoryById(Long.parseLong(categoryId));
        if(Objects.isNull(categoryResponseDto)){
            throw new CustomExceptions(CheckedExceptions.INVALID_CATEGORY);
        }
        List<String> categories = new ArrayList<>();
        categories.add(categoryId);
        // show all product in that category, and it's respective sub-categories and sibling categories
        List<Category> subCategories = associationService.fetchAllRelatedCategories(categoryResponseDto.getId());
        List<Long> productList = associationService.findProductsByRelationAndMainCategory(CategoryRelations.PRODUCT, categoryId);
        List<ProductCatalogue> productCatalogueList = catalogueRepository.findAll().stream()
                .filter(productCatalogue ->  productList.contains(productCatalogue.getProductId()))
                .collect(Collectors.toList());

        return productCatalogueList.stream().map(productCatalogue -> transformToResponseDto(productCatalogue)).collect(Collectors.toList());

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
            productCatalogue -> transformToResponseDto(productCatalogue))
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
                        productCatalogue -> transformToResponseDto(productCatalogue))
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

            Brand brand = brandService.getBrandById(requestDto.getAssociatedBrand());
            catalogue.setAssociatedBrand(Objects.isNull(brand)?null:brand);
        }
        List<ProductSpecs> specsList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(requestDto.getSpecsList())){
            requestDto.getSpecsList().forEach(spec -> {
                ProductSpecs ps = new ProductSpecs();
                ps.setFeature(spec.feature());
                ps.setValue(spec.value());
                ps.setStatus(spec.status());
                ps.setCreatedBy("user");
                ps.setCreatedOn(LocalDateTime.now());
                ps.setModifiedBy("user");
                ps.setModifiedOn(LocalDateTime.now());
                specsList.add(ps);
            });
        }
        catalogue.setSpecsList(specsList);
        catalogue.setDiscount(calculateDiscount(requestDto.getMaximumRetailPrice(), requestDto.getSellingPrice()));
        catalogue.setMaximumRetailPrice(requestDto.getMaximumRetailPrice());
        catalogue.setSellingPrice(requestDto.getSellingPrice());
        catalogue.setStatus(Boolean.TRUE);
        catalogue.setCreatedBy("user");
        catalogue.setCreatedOn(LocalDateTime.now());
        catalogue.setModifiedBy("user");
        catalogue.setModifiedOn(LocalDateTime.now());
        catalogue = catalogueRepository.save(catalogue);
        // map to category
        CategoryAssociations association = new CategoryAssociations();
        association.setMainCategory(requestDto.getCategoryId());
        association.setAssociatedEntityId(catalogue.getProductId());
        association.setAssociationUUID(UUID.randomUUID().toString());
        association.setRelation(CategoryRelations.PRODUCT);
        association.setStatus(Boolean.TRUE);
        association.setCreatedBy("user");
        association.setCreatedOn(LocalDateTime.now());
        association.setModifiedBy("user");
        association.setModifiedOn(LocalDateTime.now());
        associationService.saveData(association);
        return transformToResponseDto(catalogue);
    }

    private ProductCatalogueResponseDto transformToResponseDto(ProductCatalogue catalogue) {
        BrandMiniResponseDto brandMiniResponseDto= (catalogue.getAssociatedBrand() == null)
                ?null
                :new BrandMiniResponseDto(catalogue.getAssociatedBrand().getBrandName(), catalogue.getAssociatedBrand().getBrandDescription());

        List<ProductSpecResponseDto> specList = Optional.ofNullable(catalogue.getSpecsList())
                .orElse(Collections.emptyList())
                .stream()
                .map(spec -> new ProductSpecResponseDto(spec.getFeature(), spec.getValue()))
                .toList();

        ProductCatalogueResponseDto responseDto =
            ProductCatalogueResponseDto.builder()
                .productId(catalogue.getProductId())
                .productName(catalogue.getProductName())
                .productDescription(catalogue.getProductDescription())
                    .productColor(catalogue.getProductColor())
                .productStatus(catalogue.getProductStatus())
                .associatedBrand(brandMiniResponseDto)
                .specsList(specList)
                .maximumRetailPrice(catalogue.getMaximumRetailPrice())
                .sellingPrice(catalogue.getSellingPrice())
                .discount(catalogue.getDiscount())
                .createdBy(catalogue.getCreatedBy())
                .modifiedBy(catalogue.getModifiedBy())
                .createdOn(catalogue.getCreatedOn())
                .modifiedOn(catalogue.getModifiedOn())
                .build();

        return responseDto;
    }

    private float calculateDiscount(float maximumRetailPrice, float sellingPrice) {
        return ((maximumRetailPrice-sellingPrice)/maximumRetailPrice)*100;
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
        if(!catalogue.getAssociatedBrand().getId().equals(Long.parseLong(requestDto.getAssociatedBrand()))){
            if(Objects.nonNull(requestDto.getAssociatedBrand())){
                Brand brand = brandService.getBrandById(requestDto.getAssociatedBrand());
                catalogue.setAssociatedBrand(Objects.isNull(brand)?null:brand);
            }
        }
        catalogue.setProductStatus(requestDto.getProductStatus());
        catalogue.setMaximumRetailPrice(requestDto.getMaximumRetailPrice());
        catalogue.setDiscount(calculateDiscount(requestDto.getMaximumRetailPrice(), requestDto.getSellingPrice()));

        List<ProductSpecs> specsList = new ArrayList<>();
        Set<String> existingSpecs = catalogue.getSpecsList().stream()
                .map(ProductSpecs::getFeature)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if(!CollectionUtils.isEmpty(requestDto.getSpecsList())){
            requestDto.getSpecsList().forEach(spec -> {
                if(!existingSpecs.contains(spec.feature())){
                    ProductSpecs ps = new ProductSpecs();
                    ps.setFeature(spec.feature());
                    ps.setValue(spec.value());
                    ps.setStatus(spec.status());
                    ps.setCreatedBy("user");
                    ps.setCreatedOn(LocalDateTime.now());
                    ps.setModifiedBy("user");
                    ps.setModifiedOn(LocalDateTime.now());
                    specsList.add(ps);
                }else{
                    // todo
                }

            });
        }
        catalogue.setSpecsList(specsList);

        catalogue = catalogueRepository.save(catalogue);

        return transformToResponseDto(catalogue);
    }

    @Override
    public boolean removeProductCatalogue(String productId) {
        Optional<ProductCatalogue> optionalProductCatalogue = catalogueRepository.findById(Long.parseLong(productId));
        if(optionalProductCatalogue.isEmpty()){
            return false;
        }
        ProductCatalogue catalogue = optionalProductCatalogue.get();
        catalogue.setStatus(false);
        catalogue.setModifiedBy("user");
        catalogue.setModifiedOn(LocalDateTime.now());
        catalogue = catalogueRepository.save(catalogue);
        return true;
    }
}
