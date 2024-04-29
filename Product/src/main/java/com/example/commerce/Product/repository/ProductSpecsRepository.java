package com.example.commerce.Product.repository;

import com.example.commerce.Product.model.entity.ProductSpecs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecsRepository extends JpaRepository<ProductSpecs,Long> {

}
