package com.example.commerce.Product.controller;

import com.example.commerce.Product.exceptions.CustomExceptions;
import com.example.commerce.Product.model.DTO.Response.InventoryResponseDto;
import com.example.commerce.Product.service.InventoryService;
import com.example.commerce.Product.utils.enums.CheckedExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory/v1")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/viewAll")
    public ResponseEntity viewAllInventory(){
        log.info("initiating endpoint to GET all inventories available");
        List<InventoryResponseDto> responseDtoList = inventoryService.getAllInventories();
        if (responseDtoList.isEmpty()){
            throw new CustomExceptions(CheckedExceptions.NO_CONTENT_AVAILABLE);
        }
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/view/{inventoryId}")
    public ResponseEntity viewInventoryById(@PathVariable(name = "inventoryId")String inventoryId){
        log.info("initiating endpoint to GET by inventory id:: {}", inventoryId);
        InventoryResponseDto responseDto = inventoryService.getInventoryById(inventoryId);
        if (responseDto==null){
            throw new CustomExceptions(CheckedExceptions.NO_CONTENT_AVAILABLE);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

//    @PostMapping("/view/filter")
//    public ResponseEntity viewInventoryByFilter(){
//        return null;
//    }

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
