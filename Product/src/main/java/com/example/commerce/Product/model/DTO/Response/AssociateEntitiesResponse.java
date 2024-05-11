package com.example.commerce.Product.model.DTO.Response;

import com.example.commerce.Product.utils.enums.CategoryRelations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AssociateEntitiesResponse {

    private long entityId;
    private String entityName;
    private CategoryRelations relations;
}
