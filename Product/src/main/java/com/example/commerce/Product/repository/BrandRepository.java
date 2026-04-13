package com.example.commerce.Product.repository;

import com.example.commerce.Product.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByStatus(boolean b);
    Brand findByIdAndStatus(long parseLong, boolean b);
}
