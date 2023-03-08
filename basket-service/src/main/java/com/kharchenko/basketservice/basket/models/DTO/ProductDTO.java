package com.kharchenko.basketservice.basket.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kharchenko.basketservice.basket.models.enums.ProductType;
import com.kharchenko.basketservice.basket.models.enums.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("number")
    private Long number;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private ProductType type;
    @JsonProperty("unit")
    private ProductUnit unit;
    @JsonProperty("quantity")
    private Double quantity;
    @JsonProperty("price")
    private Double price;
}

