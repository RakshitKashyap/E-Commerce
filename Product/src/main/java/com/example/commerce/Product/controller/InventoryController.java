package com.example.commerce.Product.controller;

import com.example.commerce.Product.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory/v1")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/viewAll")
    public ResponseEntity viewAllInventory(){
        return null;
    }

    @GetMapping("/view/{inventoryId}")
    public ResponseEntity viewInventoryById(){
        return null;
    }

    @PostMapping("/view/filter")
    public ResponseEntity viewInventoryByFilter(){
        return null;
    }

    @PostMapping("/notify")
    public ResponseEntity inventoryNotify(){
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity addNewInventory(){return null;}

    @PutMapping("/updateInventory/{invemtoryId}")
    public ResponseEntity updateInventory(){return null;}

    @DeleteMapping("/shutdown/{inventoryId}")
    public ResponseEntity shutdownInventory(){return null;}
}
