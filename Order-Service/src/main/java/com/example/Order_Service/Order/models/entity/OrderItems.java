package com.example.Order_Service.Order.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table
public class OrderItems extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;

    private int quantity;

    private Double basePrice;
}
