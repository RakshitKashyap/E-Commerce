package com.example.Order_Service.Order.repository;

import com.example.Order_Service.Order.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
