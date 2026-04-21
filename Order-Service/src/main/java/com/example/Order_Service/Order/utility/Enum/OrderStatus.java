package com.example.Order_Service.Order.utility.Enum;

import lombok.Getter;


@Getter
public enum OrderStatus {

  PENDING(1, "Pending"),
  CONFIRMED(2, "Confirmed"),
  SHIPPED(3, "Shipped"),
  DELIVERED(4, "Delivered"),
//  CANCELLED(5, "Cancelled"),
  RETURNED(6, "Returned"),
  INVALID(7, "Invalid"),
  INITIATED(8, "Initiated");


  private int id;
  private String statusMessage;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  private OrderStatus(int id, String statusMessage) {
    this.id = id;
    this.statusMessage = statusMessage;
  }
}