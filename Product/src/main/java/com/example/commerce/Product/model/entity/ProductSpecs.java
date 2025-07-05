package com.example.commerce.Product.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ProductSpecs extends Basic{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long specId;

    private String feature;

    private String value;

    private boolean status;
}
