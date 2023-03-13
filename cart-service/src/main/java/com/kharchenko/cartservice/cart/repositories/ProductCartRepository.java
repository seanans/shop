package com.kharchenko.cartservice.cart.repositories;

import com.kharchenko.cartservice.cart.entities.ProductCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCartRepository extends JpaRepository<ProductCartEntity, UUID> {
}
