package com.example.Order_Service.Order.service.impl;

import com.example.Order_Service.Order.repository.OrderItemsRepository;
import com.example.Order_Service.Order.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;
}
