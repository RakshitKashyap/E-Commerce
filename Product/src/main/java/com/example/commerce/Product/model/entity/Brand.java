package com.example.commerce.Product.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Brand extends Basic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brandUUID;

    private String brandName;

    private String brandDescription;

    private boolean availableStatus;

}
