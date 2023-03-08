package com.kharchenko.basketservice.basket.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {

    @JsonProperty("id")
    String id;
    @JsonProperty("number")
    Long number;

}
