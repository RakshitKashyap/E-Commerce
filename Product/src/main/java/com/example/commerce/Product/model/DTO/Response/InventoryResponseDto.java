package com.example.commerce.Product.model.DTO.Response;

import com.example.commerce.Product.model.entity.Location;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class InventoryResponseDto {

    private Long id;

    private String InventoryName;

    private int maxLimit;

    private int currentHoldings;

    private boolean inventoryStatus;

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime modifiedOn;

    private String modifiedBy;

    private Location location;

}
