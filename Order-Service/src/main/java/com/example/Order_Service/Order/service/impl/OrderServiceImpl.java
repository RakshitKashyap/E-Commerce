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
import java.util.UUID;
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

        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().filter(order -> order.getOrderStatus().getId()==orderStatusId).collect(Collectors.toList());

    }

    @Override
    public Order createNewOrder(Order order) {
        order.setOrderUUID(UUID.randomUUID().toString());
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrder(String orderId, Order order) {
        Order order1 = findOrderByOrderId(orderId);
        order1.setUserId(123L);
        order1.setOrderUUID(UUID.randomUUID().toString());
        order1.setPaymentId(order.getPaymentId());
        order1.setOrderStatus(order.getOrderStatus());
        order1.setOrderDateTime(order.getOrderDateTime());
        order1.setTotalAmount(order.getTotalAmount());
        orderRepository.save(order1);
        return order1;
    }

    @Override
    public boolean deleteOrder(String orderId) {
        Order order = findOrderByOrderId(orderId);
        if(order==null)
            return false;
        order.setStatus(false);
        orderRepository.save(order);
        return true;
    }
}
