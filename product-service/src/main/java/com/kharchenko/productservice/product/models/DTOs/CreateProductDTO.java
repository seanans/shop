package com.kharchenko.productservice.product.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kharchenko.productservice.product.models.enums.ProductType;
import lombok.Getter;
import lombok.Setter;
import com.kharchenko.productservice.product.models.enums.ProductUnit;

@Getter
@Setter
public class CreateProductDTO {
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
}
