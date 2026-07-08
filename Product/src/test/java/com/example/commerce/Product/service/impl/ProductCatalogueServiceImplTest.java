package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.model.DTO.Request.ProductCatalogueRequestDto;
import com.example.commerce.Product.model.DTO.Request.ProductSpecRequestDto;
import com.example.commerce.Product.model.DTO.Response.ProductCatalogueResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.CategoryAssociations;
import com.example.commerce.Product.model.entity.ProductCatalogue;
import com.example.commerce.Product.model.entity.ProductSpecs;
import com.example.commerce.Product.repository.ProductCatalogueRepository;
import com.example.commerce.Product.service.BrandService;
import com.example.commerce.Product.service.CategoryAssociationService;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.service.ProductSpecService;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import com.example.commerce.Product.utils.enums.ProductColor;
import com.example.commerce.Product.utils.enums.ProductStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductCatalogueServiceImplTest {

    @Mock
    private ProductCatalogueRepository catalogueRepository;

    @Mock
    private ProductSpecService productSpecService;

    @Mock
    private CategoryAssociationService associationService;

    @Mock
    private BrandService brandService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductCatalogueServiceImpl catalogueService;

    @Test
    void getAllAvailableProductsReturnsOnlyActiveProducts() {
        ProductCatalogue activeProduct = product(1L, "Phone", true, 50000F, 45000F);
        ProductCatalogue inactiveProduct = product(2L, "Old Phone", false, 30000F, 25000F);
        when(catalogueRepository.findAll()).thenReturn(List.of(activeProduct, inactiveProduct));

        List<ProductCatalogueResponseDto> response = catalogueService.getAllAvailableProducts();

        assertAll(
                () -> assertThat(response).hasSize(1),
                () -> assertThat(response.get(0).getProductId()).isEqualTo(1L),
                () -> assertThat(response.get(0).getProductName()).isEqualTo("Phone"),
                () -> assertThat(response.get(0).getAssociatedBrand().brandName()).isEqualTo("Acme"),
                () -> assertThat(response.get(0).getSpecsList()).hasSize(1)
        );
    }

    @Test
    void addNewProductPersistsProductAndCategoryAssociation() {
        ProductCatalogueRequestDto request = productRequest();
        Brand brand = brand(10L);
        when(brandService.getBrandById("10")).thenReturn(brand);
        when(catalogueRepository.save(any(ProductCatalogue.class))).thenAnswer(invocation -> {
            ProductCatalogue saved = invocation.getArgument(0);
            saved.setProductId(99L);
            return saved;
        });

        ProductCatalogueResponseDto response = catalogueService.addNewProduct(request);

        ArgumentCaptor<ProductCatalogue> productCaptor = ArgumentCaptor.forClass(ProductCatalogue.class);
        ArgumentCaptor<CategoryAssociations> associationCaptor = ArgumentCaptor.forClass(CategoryAssociations.class);
        verify(catalogueRepository).save(productCaptor.capture());
        verify(associationService).saveData(associationCaptor.capture());

        ProductCatalogue savedProduct = productCaptor.getValue();
        CategoryAssociations savedAssociation = associationCaptor.getValue();

        assertAll(
                () -> assertThat(response.getProductId()).isEqualTo(99L),
                () -> assertThat(response.getProductName()).isEqualTo("Laptop"),
                () -> assertThat(response.getDiscount()).isEqualTo(25F),
                () -> assertThat(savedProduct.getAssociatedBrand()).isSameAs(brand),
                () -> assertThat(savedProduct.getSpecsList()).hasSize(1),
                () -> assertThat(savedProduct.isStatus()).isTrue(),
                () -> assertThat(savedAssociation.getMainCategory()).isEqualTo(5L),
                () -> assertThat(savedAssociation.getAssociatedEntityId()).isEqualTo(99L),
                () -> assertThat(savedAssociation.getRelation()).isEqualTo(CategoryRelations.PRODUCT),
                () -> assertThat(savedAssociation.isStatus()).isTrue()
        );
    }

    @Test
    void removeProductCatalogueSoftDeletesExistingProduct() {
        ProductCatalogue product = product(7L, "Tablet", true, 20000F, 18000F);
        when(catalogueRepository.findById(7L)).thenReturn(Optional.of(product));
        when(catalogueRepository.save(any(ProductCatalogue.class))).thenAnswer(invocation -> invocation.getArgument(0));

        boolean removed = catalogueService.removeProductCatalogue("7");

        ArgumentCaptor<ProductCatalogue> productCaptor = ArgumentCaptor.forClass(ProductCatalogue.class);
        verify(catalogueRepository).save(productCaptor.capture());

        assertAll(
                () -> assertThat(removed).isTrue(),
                () -> assertThat(productCaptor.getValue().isStatus()).isFalse(),
                () -> assertThat(productCaptor.getValue().getModifiedBy()).isEqualTo("user"),
                () -> assertThat(productCaptor.getValue().getModifiedOn()).isNotNull()
        );
    }

    private ProductCatalogueRequestDto productRequest() {
        ProductCatalogueRequestDto request = new ProductCatalogueRequestDto(
                "Laptop",
                5L,
                ProductColor.SILVER,
                "10",
                ProductStatus.AVAILABLE
        );
        request.setProductDescription("Portable workstation");
        request.setSpecsList(List.of(new ProductSpecRequestDto("RAM", "16GB", true)));
        request.setMaximumRetailPrice(100000F);
        request.setSellingPrice(75000F);
        return request;
    }

    private ProductCatalogue product(Long id, String name, boolean status, float mrp, float sellingPrice) {
        ProductCatalogue product = new ProductCatalogue();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductDescription(name + " description");
        product.setProductColor(ProductColor.BLACK);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setAssociatedBrand(brand(1L));
        product.setSpecsList(List.of(productSpec("Storage", "128GB")));
        product.setMaximumRetailPrice(mrp);
        product.setSellingPrice(sellingPrice);
        product.setDiscount(((mrp - sellingPrice) / mrp) * 100);
        product.setStatus(status);
        product.setCreatedBy("user");
        product.setModifiedBy("user");
        return product;
    }

    private Brand brand(Long id) {
        Brand brand = new Brand();
        brand.setId(id);
        brand.setBrandName("Acme");
        brand.setBrandDescription("Acme brand");
        brand.setAvailableStatus(true);
        brand.setStatus(true);
        return brand;
    }

    private ProductSpecs productSpec(String feature, String value) {
        ProductSpecs spec = new ProductSpecs();
        spec.setFeature(feature);
        spec.setValue(value);
        spec.setStatus(true);
        return spec;
    }
}
