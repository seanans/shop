package com.kharchenko.cartservice.cart.controllers;

import com.kharchenko.cartservice.cart.models.DTO.CartDTO;
import com.kharchenko.cartservice.cart.models.DTO.CreateCartDTO;
import com.kharchenko.cartservice.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    private List<CartDTO> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/filter")
    private ResponseEntity<CartDTO> getCartById(@RequestParam(value = "id") UUID id) {
        return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<CartDTO> createCart(@RequestBody CreateCartDTO cart) {
        return new ResponseEntity<>(cartService.createCart(cart), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    private ResponseEntity<CartDTO> updateCart(@RequestParam(value = "id") UUID id, @RequestBody CreateCartDTO cart) {
        return new ResponseEntity<>(cartService.updateCart(cart, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    private HttpStatus deleteProduct(@RequestParam(value = "id") UUID id) {
        cartService.deleteCart(id);
        return HttpStatus.ACCEPTED;
    }
}
