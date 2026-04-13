package com.example.Order_Service.Order.controller;

import com.example.Order_Service.Order.models.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderedItemsImplController implements OrderedItemsController{

    @Override
    public ResponseEntity addItemsToOrder(String orderId) {
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity getAllItemsOfOrder(String orderId) {
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity getSpecificItemOfOrder(String orderId, String itemId) {
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity updateSpecificItemOfOrder(String orderId, String itemId) {
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity deleteSpecificItemOfOrder(String orderId, String itemId) {
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity getAllOrders() {
        return null;
    }

    @Override
    public ResponseEntity getOrderByOrderId(String orderId) {
        return null;
    }

    @Override
    public ResponseEntity getOrderByStatus(int orderStatusId) {
        return null;
    }

    @Override
    public ResponseEntity createNewOrder(Order order) {
        return null;
    }

    @Override
    public ResponseEntity updateOrders(String orderId, Order order) {
        return null;
    }

    @Override
    public ResponseEntity deleteOrder(String orderId) {
        return null;
    }
}
