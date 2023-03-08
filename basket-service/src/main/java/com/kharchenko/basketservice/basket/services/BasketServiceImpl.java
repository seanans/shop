package com.kharchenko.basketservice.basket.services;

import com.kharchenko.basketservice.basket.models.DTO.BasketDTO;
import com.kharchenko.basketservice.basket.models.DTO.CreateBasketDTO;
import com.kharchenko.basketservice.basket.models.DTO.FilterDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    @Override
    public BasketDTO createBasket(CreateBasketDTO basketDTO) {
        return null;
    }

    @Override
    public BasketDTO getBasketByFilter(FilterDTO filter) {
        return null;
    }

    @Override
    public BasketDTO addProductToBasket() {
        return null;
    }

    @Override
    public BasketDTO removeProductFromBasket() {
        return null;
    }

    @Override
    public List<BasketDTO> getAllBaskets() {
        return null;
    }
}