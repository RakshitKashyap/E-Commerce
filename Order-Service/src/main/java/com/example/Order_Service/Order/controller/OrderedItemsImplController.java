package com.example.Order_Service.Order.controller;

import com.example.Order_Service.Order.models.Dto.ResponseDto.ResponseData;
import com.example.Order_Service.Order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Slf4j
@RequiredArgsConstructor
public class OrderedItemsImplController implements OrderedItemsController{

    private final OrderItemService itemService;


    @Override
    public ResponseEntity addItemsToCart(Object requestDto, String orderId) {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }

    @Override
    public ResponseEntity getAllItemsOfCart(String orderId) {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }

    @Override
    public ResponseEntity updateSpecificItemOfCart(String orderId, String itemId, Object requestBody) {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }

    @Override
    public ResponseEntity deleteSpecificItemOfCart(String orderId, String itemId) {
        log.info("Controller", "Initiating endpoint to create new Order");
        return null;
    }
}
