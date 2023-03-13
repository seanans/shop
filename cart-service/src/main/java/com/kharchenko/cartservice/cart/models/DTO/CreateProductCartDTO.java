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
public class CreateProductCartDTO {
    @JsonProperty("productId")
    private UUID productId;
    @JsonProperty("orderedQuantity")
    private Double orderedQuantity;
}
