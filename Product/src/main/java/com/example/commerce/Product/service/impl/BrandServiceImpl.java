package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Request.BrandRequestDto;
import com.example.commerce.Product.model.DTO.Response.BrandResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.Category;
import com.example.commerce.Product.repository.BrandRepository;
import com.example.commerce.Product.service.BrandService;
import com.example.commerce.Product.service.CategoryAssociationService;
import com.example.commerce.Product.service.CategoryService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<BrandResponseDto> getAll() {
        List<Brand> brands = brandRepository.findByStatus(Boolean.TRUE);
        return brands.stream().map(brand -> convertToResponseDto(brand)).collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto getBrandDtoById(String brandId) {
        if(brandId.trim().isEmpty() || Objects.isNull(brandId.trim())){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        Brand brand = brandRepository.findByIdAndStatus(Long.parseLong(brandId.trim()), true);
        return convertToResponseDto(brand);
    }

    public Brand getBrandById(String brandId) {
        if(brandId.trim().isEmpty() || Objects.isNull(brandId.trim())){
            throw new CustomExceptions(CheckedExceptions.INVALID_INPUT);
        }
        return brandRepository.findByIdAndStatus(Long.parseLong(brandId.trim()), true);
   }

    @Override
    public List<BrandResponseDto> getByCategoryAssociation(String categoryId) {

        Category category = categoryService.fetchCategoryByCategoryId(Long.parseLong(categoryId));

        if(Objects.isNull(category)){
            return null;
        }



        return null;
    }

    @Override
    public List<BrandResponseDto> getBestDeals(String discount) {
        int percentage = StringUtils.isEmpty(discount)?50:Integer.parseInt(discount.trim());
        return null;
    }

    @Override
    public BrandResponseDto addNewBrand(BrandRequestDto requestDto) {

        Brand brand  = new Brand();
        brand.setBrandName(requestDto.getBrandName());
        brand.setBrandDescription(requestDto.getBrandDescription());
        brand.setBrandUUID(UUID.randomUUID().toString());
        brand.setAvailableStatus(Boolean.TRUE);
        brand.setCreatedBy("user");
        brand.setCreatedOn(LocalDateTime.now());
        brand.setModifiedBy("user");
        brand.setModifiedOn(LocalDateTime.now());
        brand.setStatus(Boolean.TRUE);
        brand = brandRepository.save(brand);
        return convertToResponseDto(brand);
    }

    private BrandResponseDto convertToResponseDto(Brand brand) {
        BrandResponseDto responseDto = new BrandResponseDto();
        responseDto.setId(brand.getId());
        responseDto.setBrandName(brand.getBrandName());
        responseDto.setBrandDescription(brand.getBrandDescription());
        responseDto.setAvailableStatus(brand.isAvailableStatus());
        responseDto.setCreatedBy(brand.getCreatedBy());
        responseDto.setModifiedBy(brand.getModifiedBy());
        responseDto.setCreatedOn(brand.getCreatedOn());
        responseDto.setModifiedOn(brand.getModifiedOn());
        return  responseDto;
    }

    @Override
    public BrandResponseDto updateBrand(Long brandId, BrandRequestDto requestDto) {
        Brand brand = brandRepository.findByIdAndStatus(brandId, Boolean.TRUE);
        if(Objects.isNull(brand)){
            throw new CustomExceptions(CheckedExceptions.NO_CONTENT_AVAILABLE);
        }
        brand.setBrandDescription(requestDto.getBrandDescription());
        brand.setBrandName(requestDto.getBrandName());
        brand.setModifiedOn(LocalDateTime.now());
        brand.setModifiedBy("user");
        brand = brandRepository.save(brand);
        return convertToResponseDto(brand);
    }

    @Override
    public boolean removeBrand(Long brandId) {
        Brand brand = brandRepository.findByIdAndStatus(brandId, Boolean.TRUE);
        brand.setStatus(Boolean.FALSE);
        brand.setModifiedOn(LocalDateTime.now());
        brand.setModifiedBy("user");
        brand = brandRepository.save(brand);
        return false;
    }
}


//https://novellive.app/book/supreme-harem-god-system/cchapter-250-so-this-mist-is-it-from-your-curse