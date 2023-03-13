package com.kharchenko.cartservice.cart.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @Column(name = "orderedQuantity", nullable = false)
    private Double orderedQuantity;

}
