package com.kharchenko.productservice.product.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.kharchenko.productservice.product.models.enums.ProductType;
import com.kharchenko.productservice.product.models.enums.ProductUnit;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "number", unique = true, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private ProductType type;

    @Enumerated(EnumType.ORDINAL)
    private ProductUnit unit;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "price", nullable = false)
    private Double price;
}
