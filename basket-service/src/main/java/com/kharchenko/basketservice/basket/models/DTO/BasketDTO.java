package com.kharchenko.basketservice.basket.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("number")
    private Long number;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("products")
    List<UUID> products;
}
