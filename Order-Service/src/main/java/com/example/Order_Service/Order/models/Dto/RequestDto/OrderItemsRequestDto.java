package com.example.Order_Service.Order.models.Dto.RequestDto;

import lombok.Data;

@Data
public class OrderItemsRequestDto {

    private Long productId;

    private Long itemId;

    private int quantity;

    private Double basePrice;

}
