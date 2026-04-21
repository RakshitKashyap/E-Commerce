package com.example.Order_Service.Order.controller;

import com.example.Order_Service.Order.models.Dto.ResponseDto.ResponseData;
import com.example.Order_Service.Order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class OrderImplController implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseData getAllOrders() {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }

    @Override
    public ResponseData getOrderByOrderId(String orderId) {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }

    @Override
    public ResponseData getOrderByStatus(String orderStatus) {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }

    @Override
    public ResponseData createNewOrder(Object order) {
        log.info("Controller", "Initiating endpoint to create new Order");
//        ResponseData result = orderService.createNewOrder(order);
        return null;
    }

    @Override
    public ResponseData updateOrders(String orderId, Object order) {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }

    @Override
    public ResponseData deleteOrder(String orderId) {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }
}