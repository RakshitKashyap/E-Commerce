package com.example.Order_Service.Order.models.entity;

import com.example.Order_Service.Order.utility.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String orderUUID;

    private LocalDateTime orderDateTime;

    private Double totalAmount;

    private OrderStatus orderStatus;

    private Long paymentId;

    private boolean status;
}
