package com.example.commerce.Product.model.DTO.Request;

import com.example.commerce.Product.utils.enums.CategoryRelations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class AddAssociateRequestDTO {

    @NonNull
    private Long associatedEntityId;

    @NonNull
    private CategoryRelations relations;

}
