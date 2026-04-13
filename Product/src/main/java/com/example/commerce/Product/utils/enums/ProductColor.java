package com.example.commerce.Product.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductColor {

    WHITE(1, "White"),
    BLACK(2, "Black"),
    RED(3, "Red"),
    GREEN(4, "Green"),
    BLUE(5, "Blue"),
    VIOLET(6, "Violet"),
    GOLD(7, "Gold"),
    PINK(8, "Pink"),
    ORANGE(9, "Orange"),
    GREY(10, "Grey"),
    BROWN(11, "Brown"),
    YELLOW(12, "Yellow"),
    SILVER(13,"Silver");



    private int colorCode;
    private String color;

}
