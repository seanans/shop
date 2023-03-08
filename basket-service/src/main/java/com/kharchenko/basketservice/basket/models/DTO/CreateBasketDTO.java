package com.kharchenko.basketservice.basket.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateBasketDTO {
    @JsonProperty("number")
    private Long number;
    @JsonProperty("products")
    private List<UUID> products;
}
