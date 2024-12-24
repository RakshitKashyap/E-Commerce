package com.example.commerce.Product.service.impl;

import com.example.commerce.Product.model.DTO.Response.InventoryResponseDto;
import com.example.commerce.Product.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Override
    public List<InventoryResponseDto> getAllInventories() {
        return null;
    }

    @Override
    public InventoryResponseDto getInventoryById(String inventoryId) {
        return null;
    }
}
