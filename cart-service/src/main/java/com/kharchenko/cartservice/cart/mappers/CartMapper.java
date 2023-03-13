package com.kharchenko.cartservice.cart.mappers;

import com.kharchenko.cartservice.cart.entities.CartEntity;
import com.kharchenko.cartservice.cart.models.DTO.CartDTO;
import com.kharchenko.cartservice.cart.models.DTO.CreateCartDTO;
import com.kharchenko.cartservice.cart.models.DTO.CreateProductCartDTO;
import com.kharchenko.cartservice.cart.models.DTO.ProductCartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface CartMapper {

    CartDTO createCartDTOToCartDTO(CreateCartDTO cartDTO);
    CartEntity cartDTOToCartEntity(CartDTO cartDTO);
    CartEntity createCartDTOToCartEntity(CreateCartDTO cartDTO);
    List<CartDTO> cartEntityListToCartDTOList(List<CartEntity> cartEntities);
    List<CartEntity> cartDTOListToCartEntityList(List<CartDTO> cartDTOS);
    ProductCartDTO createProductCartDTOToProductCartDTO(CreateProductCartDTO cartDTO);
}
