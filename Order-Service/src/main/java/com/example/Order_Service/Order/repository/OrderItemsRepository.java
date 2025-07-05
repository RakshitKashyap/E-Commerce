package com.example.Order_Service.Order.repository;

import com.example.Order_Service.Order.models.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {}
