package com.example.Order_Service.Order.controller;

import com.example.Order_Service.Order.service.OrderItemService;
import com.example.Order_Service.Order.service.OrderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/{orderId}")
public interface OrderedItemsController extends OrderController{

    @PostMapping("/items/create")
    public abstract ResponseEntity addItemsToOrder(@PathVariable(name = "orderId")String orderId);

    @GetMapping("/items")
    public abstract ResponseEntity getAllItemsOfOrder(@PathVariable(name = "orderId")String orderId);

    @GetMapping("/items/{itemId}")
    public abstract ResponseEntity getSpecificItemOfOrder( @PathVariable(name = "orderId")String orderId , @PathVariable(name = "itemId")String itemId);

    @PutMapping("/items/{itemId}")
    public abstract ResponseEntity updateSpecificItemOfOrder(@PathVariable(name = "orderId")String orderId, @PathVariable(name = "itemId")String itemId);

    @DeleteMapping("/items/{itemId}")
    public abstract ResponseEntity deleteSpecificItemOfOrder(@PathVariable(name = "orderId")String orderId, @PathVariable(name = "itemId")String itemId);

}
