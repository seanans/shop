package com.kharchenko.basketservice.basket.controllers;

import com.kharchenko.basketservice.basket.models.DTO.BasketDTO;
import com.kharchenko.basketservice.basket.models.DTO.CreateBasketDTO;
import com.kharchenko.basketservice.basket.models.DTO.FilterDTO;
import com.kharchenko.basketservice.basket.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping("")
    private ResponseEntity<BasketDTO> createBasket(@RequestBody CreateBasketDTO basketDTO) {
        return new ResponseEntity<>(basketService.createBasket(basketDTO), HttpStatus.CREATED);
    }

    @PostMapping("/filter")
    private ResponseEntity<BasketDTO> getBasketById(@RequestBody FilterDTO filter) {
        return new ResponseEntity<>(basketService.getBasketByFilter(filter), HttpStatus.OK);
    }


}
