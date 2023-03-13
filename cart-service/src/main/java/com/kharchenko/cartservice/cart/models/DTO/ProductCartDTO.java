package com.kharchenko.cartservice.cart.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDTO {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("productId")
    private UUID productId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("orderedQuantity")
    private Double orderedQuantity;
}
