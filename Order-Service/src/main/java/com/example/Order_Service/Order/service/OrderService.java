package com.example.Order_Service.Order.service;

import com.example.Order_Service.Order.models.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order findOrderByOrderId(String orderId);

    List<Order> findOrderByStatusId(int orderStatusId);

    Order createNewOrder(Order order);

    Order updateOrder(String orderId, Order order);
}
