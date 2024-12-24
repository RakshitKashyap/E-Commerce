package com.example.commerce.Product.repository;

import com.example.commerce.Product.model.entity.Brand;
import com.example.commerce.Product.model.entity.ProductCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCatalogueRepository extends JpaRepository<ProductCatalogue, Long> {
    List<ProductCatalogue> findByAssociatedBrandAndStatus(Brand brand, boolean b);
}
