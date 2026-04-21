package com.example.Order_Service.Order.repository;

import com.example.Order_Service.Order.models.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findByIdAndStatus(long parseLong, Boolean aTrue);
}
