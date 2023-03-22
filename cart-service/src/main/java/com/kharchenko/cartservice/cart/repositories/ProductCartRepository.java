package com.kharchenko.cartservice.cart.repositories;

import com.kharchenko.cartservice.cart.entities.ProductCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCartEntity, UUID> {
}
