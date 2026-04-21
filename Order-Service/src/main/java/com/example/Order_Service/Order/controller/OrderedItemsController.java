package com.example.Order_Service.Order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(BaseController.V1+"/items")
public interface OrderedItemsController{

    @PostMapping("/create")
    public abstract ResponseEntity addItemsToCart(@RequestBody Object requestDto, @RequestParam(name = "orderId", required = false)String orderId);

    @GetMapping("/")
    public abstract ResponseEntity getAllItemsOfCart(@RequestParam(name = "orderId", required = true)String orderId);

    @PutMapping("/{itemId}")
    public abstract ResponseEntity updateSpecificItemOfCart(@RequestParam(name = "orderId", required = true)String orderId, @PathVariable(name = "itemId")String itemId, @RequestBody Object requestBody);

    @DeleteMapping("/{itemId}")
    public abstract ResponseEntity deleteSpecificItemOfCart(@RequestParam(name = "orderId", required = true)String orderId, @PathVariable(name = "itemId")String itemId);

}
