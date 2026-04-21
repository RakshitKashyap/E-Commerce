package com.example.Order_Service.Order.controller;


import com.example.Order_Service.Order.models.Dto.ResponseDto.ResponseData;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(BaseController.V1+"/order")
public interface OrderController extends BaseController{

    @GetMapping("/")
    ResponseData getAllOrders();

    @GetMapping("/{orderId}")
    ResponseData getOrderByOrderId(@PathVariable(name = "orderId")String orderId);

    @GetMapping("/status/{status}")
    ResponseData getOrderByStatus(@PathVariable(name = "status")String orderStatus);

    @PostMapping("/new")
    ResponseData createNewOrder(@Validated @RequestBody Object order);

    @PutMapping("/update/{orderId}")
    ResponseData updateOrders(@PathVariable(name = "orderId")String orderId, @RequestBody Object order);

    @DeleteMapping("/delete/{orderId}")
    ResponseData deleteOrder(@PathVariable(name = "orderId")String orderId);
}
