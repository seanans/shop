package com.kharchenko.cartservice.cart.services;

import com.kharchenko.cartservice.cart.models.DTO.CartDTO;
import com.kharchenko.cartservice.cart.models.DTO.CreateCartDTO;
import com.kharchenko.cartservice.cart.models.DTO.RemoveProductDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartDTO> getAllCarts();
    CartDTO createCart(CreateCartDTO cart);
    CartDTO getCartById(UUID id);
    CartDTO addProductsToCart(CreateCartDTO cart, UUID id);

    CartDTO removeProductsFromDTO(RemoveProductDTO cart, UUID id);
    void deleteCart(UUID id);
}
