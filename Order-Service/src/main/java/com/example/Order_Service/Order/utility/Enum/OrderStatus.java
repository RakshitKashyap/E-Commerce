package com.example.Order_Service.Order.utility.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum OrderStatus {
  PENDING(1, "Pending"),
  CONFIRMED(2, "Confirmed"),
  SHIPPED(3, "Shipped"),
  DELIVERED(4, "Delivered"),
  CANCELLED(5, "Cancelled"),
  RETURNED(6, "Returned"),
  INVALID(7, "Invalid");

  private int id;
  private String status;

  public static OrderStatus getValue(int id) {
    return Arrays.stream(OrderStatus.values())
        .filter(status -> status.getId() == id)
        .findFirst()
        .orElse(OrderStatus.INVALID);
  }
}