package com.example.Order_Service.Order.controller;

import com.example.Order_Service.Order.models.entity.Order;
import com.example.Order_Service.Order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/orders")
@Slf4j
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity getAllOrders(){
        log.info("initiating endpoint to get all Orders:");
        List<Order> orderList = orderService.getAllOrders();
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrderByOrderId(@PathVariable(name = "orderId")String orderId) {
        log.info("initiating endpoint to get Order by OrderId :{}", orderId);
        Order order = orderService.findOrderByOrderId(orderId);
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/status/{status")
    public ResponseEntity getOrderByStatus(@PathVariable(name="orderStatus")int orderStatusId){
        log.info("initiating endpoint to get Order by statusID :{}", orderStatusId);
        List<Order> orderList = orderService.findOrderByStatusId(orderStatusId);
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/new")
    public ResponseEntity createNewOrder(@Validated @RequestBody Order order){
        log.info("initiating endpoint to create new Order");

        Order orderResponse = orderService.createNewOrder(order);
        return new ResponseEntity(null, HttpStatusCode.valueOf(201));
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrders(@PathVariable(name = "orderId")String orderId, @RequestBody Order order){
        log.info("initiating endpoint to update Order by OrderId :{}", orderId);
        Order orderResponse = orderService.updateOrder(orderId, order);
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable(name = "orderId")String orderId){
        log.info("initiating endpoint to delete Order by OrderId :{}", orderId);
        return new ResponseEntity(null, HttpStatusCode.valueOf(200));
    }




}

