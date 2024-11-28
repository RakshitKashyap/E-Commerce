package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.model.DTO.Request.BrandRequestDto;
import com.example.commerce.Product.model.DTO.Response.BrandResponseDto;
import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.Category;
import com.example.commerce.Product.repository.BrandRepository;
import com.example.commerce.Product.repository.CategoryAssociationRepository;
import com.example.commerce.Product.repository.CategoryRepository;
import com.example.commerce.Product.service.BrandService;
import com.example.commerce.Product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryAssociationRepository associationRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<BrandResponseDto> getAll() {
        List<Brand> brands = brandRepository.findAllByAvailableStatus(true);
        return brands.stream().map(brand -> transformToResponse(brand)).collect(Collectors.toList());
    }

    private BrandResponseDto transformToResponse(Brand brand) {
        BrandResponseDto responseDto = new BrandResponseDto();
        BeanUtils.copyProperties(brand, responseDto);
        return responseDto;
    }

    @Override
    public BrandResponseDto getBrandById(String brandId) {
        Brand brand = brandRepository.findByIdAndAvailableStatus(Long.parseLong(brandId.trim()), true);
        return transformToResponse(brand);
    }

    /**
     * userMaketingAttribute
     *
     * size : 500 for pageable
     */

    @Override
    public List<BrandResponseDto> getByCategoryAssociation(String categoryId) {

        Optional<Category> optionalCategory = categoryRepository.findById(Long.parseLong(categoryId));
        Category category = (optionalCategory.isPresent())?optionalCategory.get():null;

        if(Objects.isNull(category)){
            return null;
        }



        /**
         * check for the nulol val
         *
         * transactional value
         *
         * checkn for the inventor
         */


        return null;
    }

    @Override
    public List<BrandResponseDto> getBestDeals(int i) {
        return null;
    }

    @Override
    public BrandResponseDto addNewBrand(BrandRequestDto requestDto) {
        return null;
    }

    @Override
    public BrandResponseDto updateBrand(String brandId, BrandRequestDto requestDto) {
        return null;
    }

    @Override
    public boolean removeBrand(String brandId) {
        return false;
    }
}
