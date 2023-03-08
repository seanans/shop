package com.kharchenko.basketservice.basket.repositories;

import com.kharchenko.basketservice.basket.entities.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusketRepository extends JpaRepository<BasketEntity, UUID> {
}
