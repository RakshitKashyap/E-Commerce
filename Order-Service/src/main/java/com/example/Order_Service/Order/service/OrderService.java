package com.example.Order_Service.Order.service;

import com.example.Order_Service.Order.models.Dto.ResponseDto.ResponseData;
import com.example.Order_Service.Order.models.entity.Orders;

import java.util.List;

public interface OrderService {
    ResponseData getAllOrders();

    ResponseData findOrderByOrderId(String orderId);

    ResponseData findOrderByStatusId(int orderStatusId);

    ResponseData createNewOrder(Object orders);

    ResponseData updateOrder(String orderId, Orders orders);

    ResponseData deleteOrder(String orderId);

    Orders saveOrderDetail(Orders orders);
}
