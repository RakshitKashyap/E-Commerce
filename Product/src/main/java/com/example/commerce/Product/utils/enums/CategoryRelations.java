package com.example.commerce.Product.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryRelations {
    CHILD_CATEGORY,
    PARENT_CATEGORY,
    PRODUCT,
    ANONYMOUS,
    BRAND,
    SIBLING_CATEGORY;
}
