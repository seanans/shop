package com.kharchenko.basketservice.basket.services;

import com.kharchenko.basketservice.basket.models.DTO.BasketDTO;
import com.kharchenko.basketservice.basket.models.DTO.CreateBasketDTO;
import com.kharchenko.basketservice.basket.models.DTO.FilterDTO;

import java.util.List;

public interface BasketService {
    BasketDTO createBasket(CreateBasketDTO basketDTO);
    BasketDTO getBasketByFilter(FilterDTO filter);
    BasketDTO addProductToBasket();
    BasketDTO removeProductFromBasket();

    List<BasketDTO> getAllBaskets();
}
