package com.kharchenko.productservice.product.repositories;

import com.kharchenko.productservice.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query(value = "SELECT * FROM public.product_entity WHERE number = ?", nativeQuery = true)
    Optional<ProductEntity> findByNumber(Long number);
}
