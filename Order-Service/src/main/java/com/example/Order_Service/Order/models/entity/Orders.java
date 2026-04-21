package com.example.Order_Service.Order.models.entity;

import com.example.Order_Service.Order.utility.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="orders")
public class Orders extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderedBy;

    private String orderUUID;

    private LocalDateTime orderDateTime;

    private Double totalAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItems> orderItems;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private Long paymentId;
}
