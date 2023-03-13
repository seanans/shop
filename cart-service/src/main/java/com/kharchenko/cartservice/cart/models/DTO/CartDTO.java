package com.kharchenko.cartservice.cart.models.DTO;

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
public class CartDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("number")
    private Long number;
    @JsonProperty("totalPrice")
    private Long totalPrice;
    @JsonProperty("products")
    private List<ProductCartDTO> products;

}
