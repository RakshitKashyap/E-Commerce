package com.example.commerce.Product.repository;

import com.example.commerce.Product.model.entity.CategoryAssociations;
import com.example.commerce.Product.utils.enums.CategoryRelations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryAssociationRepository extends JpaRepository<CategoryAssociations, Long> {
    List<CategoryAssociations> findByRelationAndMainCategory(CategoryRelations product, long parseLong);
}
