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
@Getter
@Setter
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String locationUUID;

    private String addressLineOne;

    private String addressLineTwo;

    private String mobile;

    private String zipCode;

    private String city;

    private String state;

    private String country;

    private String lingualPrimary;

    private String lingualSecondary;

    private boolean status;

}
