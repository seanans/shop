package com.kharchenko.cartservice.cart.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue()
    @Column(name = "cart_id", nullable = false)
    private UUID id;

    @Column(name = "number", unique = true, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(name = "total_price")
    private Long totalPrice;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    List<ProductCartEntity> orderedProducts;
}
