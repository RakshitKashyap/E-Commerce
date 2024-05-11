package com.example.commerce.Product.model.DTO.Response;

import com.example.commerce.Product.model.DTO.Request.AddAssociateRequestDTO;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@AllArgsConstructor
@Getter
public class AssociateResponseDto {

    private long categoryId;
    private String categoryName;
    private List<AssociateEntitiesResponse> associatedEntity;
}
