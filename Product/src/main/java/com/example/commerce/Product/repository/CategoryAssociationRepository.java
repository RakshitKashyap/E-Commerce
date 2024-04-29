package com.example.commerce.Product.repository;

import com.example.commerce.Product.model.entity.CategoryAssociations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryAssociationRepository extends JpaRepository<CategoryAssociations, Long> {}
