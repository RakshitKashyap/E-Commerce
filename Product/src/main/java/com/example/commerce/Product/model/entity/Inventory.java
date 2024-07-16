package com.example.commerce.Product.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String inventoryUUID;

    private String InventoryName;

    private int maxLimit;

    private int currentHoldings;

    private boolean inventoryStatus;

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime modifiedOn;

    private String modifiedBy;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private boolean status;
}
