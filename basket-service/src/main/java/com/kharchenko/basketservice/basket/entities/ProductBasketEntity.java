package com.kharchenko.basketservice.basket.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "product_basket")
public class ProductBasketEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "basket_id", nullable = false)
    private BasketEntity basket;

    @Column(name = "quantity", nullable = false)
    private Long quantity;
}
