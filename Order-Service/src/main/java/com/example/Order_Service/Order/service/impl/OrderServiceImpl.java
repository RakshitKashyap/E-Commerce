package com.example.Order_Service.Order.service.impl;

import com.example.Order_Service.Order.models.entity.Order;
import com.example.Order_Service.Order.repository.OrderRepository;
import com.example.Order_Service.Order.service.OrderService;
import com.example.Order_Service.Order.utility.Enum.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().filter(order -> order.isStatus()).collect(Collectors.toList());
    }

    @Override
    public Order findOrderByOrderId(String orderId) {
        if(orderId.trim().isEmpty() || Objects.isNull(orderId)){
            return null;
        }
        Order order = orderRepository.findById(Long.parseLong(orderId.trim())).get();
        if(order==null)
            return null;
        return order;
    }

    @Override
    public List<Order> findOrderByStatusId(int orderStatusId) {
        if(Objects.isNull(OrderStatus.getValue(orderStatusId))){
            return null;
        }
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().filter(order -> order.getOrderStatus().getId()==orderStatusId).collect(Collectors.toList());

    }

    @Override
    public Order createNewOrder(Order order) {
        return null;
    }

    @Override
    public Order updateOrder(String orderId, Order order) {
        return null;
    }
}
