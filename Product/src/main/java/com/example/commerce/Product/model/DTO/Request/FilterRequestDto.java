package com.example.commerce.Product.model.DTO.Request;

import com.example.commerce.Product.model.DTO.Response.CategoryResponseDto;
import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.Category;
import com.example.commerce.Product.utils.enums.ProductColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FilterRequestDto {

    private List<Brand> brandList;

    private List<CategoryResponseDto> categoryList;

    private List<ProductColor> colorList;

    private float minimumPriceRange;

    private float maximumPriceRange;

    private float leastDiscount;
}
