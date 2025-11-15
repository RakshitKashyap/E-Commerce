package com.example.commerce.Product.model.DTO.Response;

import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import lombok.*;

import java.util.List;

@Data
public class AssociateResponseDto {

    private long categoryId;
    private String categoryName;
    private List<AssociateEntitiesResponse> associatedEntity;
}
