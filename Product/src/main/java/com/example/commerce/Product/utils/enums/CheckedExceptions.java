package com.example.commerce.Product.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CheckedExceptions {

    PRODUCT_NOT_EXIST(404, "Product not Exist"),
    PRODUCT_NOT_AVAILABLE(404, "Product not Available"),
    INVALID_INPUT(500, "Invalid Input");

    private int errorCode;
    private String message;
}
