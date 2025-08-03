package com.example.Order_Service.Order.controller;

import com.example.Order_Service.Order.models.entity.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrderController extends BaseController{

    @GetMapping("/")
    public abstract ResponseEntity getAllOrders();

    @GetMapping("/{orderId}")
    public abstract ResponseEntity getOrderByOrderId(@PathVariable(name = "orderId")String orderId);

    @GetMapping("/status/{status")
    public abstract ResponseEntity getOrderByStatus(@PathVariable(name = "status")int orderStatusId);

    @PostMapping("/new")
    public abstract ResponseEntity createNewOrder(@Validated @RequestBody Order order);

    @PutMapping("/update/{orderId}")
    public abstract ResponseEntity updateOrders(@PathVariable(name = "orderId")String orderId, @RequestBody Order order);

    @DeleteMapping("/delete/{orderId}")
    public abstract ResponseEntity deleteOrder(@PathVariable(name = "orderId")String orderId);
}
