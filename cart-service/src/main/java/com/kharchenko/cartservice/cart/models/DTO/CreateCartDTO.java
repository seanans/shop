package com.kharchenko.cartservice.cart.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartDTO {

    @JsonProperty("number")
    private Long number;
    @JsonProperty("products")
    private List<CreateProductCartDTO> productCartDTOList;
}
