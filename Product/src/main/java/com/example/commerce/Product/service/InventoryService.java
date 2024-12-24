package com.example.commerce.Product.service;

import com.example.commerce.Product.model.DTO.Response.InventoryResponseDto;

import java.util.List;

public interface InventoryService {
    List<InventoryResponseDto> getAllInventories();

    InventoryResponseDto getInventoryById(String inventoryId);
}
