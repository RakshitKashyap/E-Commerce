package com.example.commerce.Product.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductColor {

    WHITE(1, "White"),
    BLACK(1, "Black"),
    RED(1, "Red"),
    GREEN(1, "Green"),
    BLUE(1, "Blue"),
    VIOLET(1, "Violet"),
    GOLD(1, "Gold"),
    PINK(1, "Pink"),
    ORANGE(1, "Orange"),
    GREY(1, "Grey"),
    BROWN(1, "Brown"),
    YELLOW(1, "Yellow");


    private int colorCode;
    private String color;

}
