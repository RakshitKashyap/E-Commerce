package com.example.commerce.Product.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductStatus {

    AVAILABLE(1, "Available"),
    NON_AVAILABLE(2, "Non-Available"),
    OUT_OF_STOCK(3, "Out of Stock"),
    PRE_ORDER(4, "Pre-Order"),
    ON_SALE(5, "On Sale"),
    COMING_SO0N(6, "Coming soon"),
    FEATURED(7, "Featured"),
    ON_HOLD(8, "On-Hold");

    private int code;
    private String status;
}
