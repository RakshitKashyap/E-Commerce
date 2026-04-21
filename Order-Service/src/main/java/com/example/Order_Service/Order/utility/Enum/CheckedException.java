package com.example.Order_Service.Order.utility.Enum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CheckedException {

    ORDER_NOT_EXIST(404, "Order not Exist"),
    PRODUCT_NOT_AVAILABLE(404, "Product not Available"),
    NO_CONTENT_AVAILABLE(204, "NO Content Available"),
    INVALID_INPUT(500, "Invalid Input"),
    DATA_NOT_SAVED(500, "Can not save data to RDB"),

    INVALID_CATEGORY(400, "Invalid Category"),
    INVALID_REQUEST(400, "Invalid Request");

    private int errorCode;
    private String message;
}
