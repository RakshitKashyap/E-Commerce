package com.example.Order_Service.Order.models.Dto.RequestDto;

import com.example.Order_Service.Order.models.entity.OrderItems;
import com.example.Order_Service.Order.utility.Enum.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequestDto {

    private List<OrderItemsRequestDto> orderItems;

}
