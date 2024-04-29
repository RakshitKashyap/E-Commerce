package com.example.commerce.Product.repository;

import com.example.commerce.Product.model.entity.ProductCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCatalogueRepository extends JpaRepository<ProductCatalogue, Long> {}
