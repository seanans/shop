package com.kharchenko.cartservice.cart.services;

import com.kharchenko.cartservice.cart.entities.CartEntity;
import com.kharchenko.cartservice.cart.entities.ProductCartEntity;
import com.kharchenko.cartservice.cart.mappers.CartMapper;
import com.kharchenko.cartservice.cart.models.DTO.*;
import com.kharchenko.cartservice.cart.repositories.CartRepository;
import com.kharchenko.cartservice.cart.repositories.ProductCartRepository;
import com.kharchenko.cartservice.exceptoins.ResourceBadRequestException;
import com.kharchenko.cartservice.exceptoins.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    private static final String EMPTY_CART = "Your cart is empty. Add some products";
    private static final String A_LOT_OF_ORDERED_PRODUCTS = "We don`t have enough product id: ";
    private static final String PRODUCT_NOT_FOUND = "We don`t have this product id: ";
    private static final String UUID_REQUIRED = "Put some id";
    private static final String CART_NOT_FOUND = "We don`t have this cart. Check the entered data";
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductCartRepository productCartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Value("${directory.product.search.url}")
    private String searchUrl;

    @Value("${directory.product.update.url}")
    private String updateUrl;

    @Override
    public List<CartDTO> getAllCarts() {
        RestTemplate restTemplate = new RestTemplate();

        List<CartEntity> cartEntities = cartRepository.findAll();
        List<CartDTO> cartDTOS = new ArrayList<>();

        for (CartEntity cartEntity : cartEntities) {

            var orderedProductsEntity = cartEntity.getOrderedProducts();
            List<ProductCartDTO> orderedProductsDTOs = new ArrayList<>();

            for (ProductCartEntity productCartEntity : orderedProductsEntity) {

                ResponseEntity<ProductDTO> responseEntity =
                        restTemplate.getForEntity(
                                searchUrl + productCartEntity.getProductId(),
                                ProductDTO.class);

                var productCart = new ProductCartDTO(
                        productCartEntity.getId(),
                        productCartEntity.getProductId(),
                        responseEntity.getBody().getName(),
                        productCartEntity.getOrderedQuantity()
                );

                orderedProductsDTOs.add(productCart);

            }

            var cartDTO = new CartDTO(
                    cartEntity.getId(),
                    cartEntity.getNumber(),
                    cartEntity.getTotalPrice(),
                    orderedProductsDTOs
            );
            cartDTOS.add(cartDTO);

        }
        return cartDTOS;
    }

    @Override
    @Transactional
    public CartDTO createCart(CreateCartDTO cart) {
        RestTemplate restTemplate = new RestTemplate();
        long totalPrice = 0;
        var cartEntity = new CartEntity();
        cartEntity.setNumber(cart.getNumber());
        cartRepository.save(cartEntity);

        var cartDTO = new CartDTO();

        var createProductCart = cart.getProductCartDTOList();
        List<ProductCartDTO> productCartDTOList = new ArrayList<>();

        List<ProductCartEntity> productCartEntities = new ArrayList<>();

        if (createProductCart.isEmpty()) {
            throw new ResourceBadRequestException(EMPTY_CART);
        }

        for (CreateProductCartDTO productCart : createProductCart) {

            ResponseEntity<ProductDTO> restProduct =
                    restTemplate.getForEntity(
                            searchUrl + productCart.getProductId(),
                            ProductDTO.class);
            if (restProduct.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                throw new RuntimeException(PRODUCT_NOT_FOUND + productCart.getProductId());
            } else {

                if (restProduct.getBody().getQuantity() < productCart.getOrderedQuantity()) {
                    throw new ResourceBadRequestException(A_LOT_OF_ORDERED_PRODUCTS + productCart.getProductId());

                } else {

                    var quantity = restProduct.getBody().getQuantity() - productCart.getOrderedQuantity();
                    var productCartDTO = new ProductCartDTO(
                            UUID.randomUUID(),
                            restProduct.getBody().getId(),
                            restProduct.getBody().getName(),
                            productCart.getOrderedQuantity());
                    productCartDTOList.add(productCartDTO);

                    var productCartEntity = new ProductCartEntity();
                    productCartEntity.setId(productCartDTO.getId());
                    productCartEntity.setProductId(productCartDTO.getProductId());
                    productCartEntity.setCart(cartEntity);
                    productCartEntity.setOrderedQuantity(productCart.getOrderedQuantity());
                    productCartEntities.add(productCartEntity);

                    var createProductDTO = new CreateProductDTO(
                            restProduct.getBody().getNumber(),
                            restProduct.getBody().getName(),
                            restProduct.getBody().getDescription(),
                            restProduct.getBody().getType(),
                            restProduct.getBody().getUnit(),
                            quantity,
                            restProduct.getBody().getPrice()
                    );

                    HttpEntity<CreateProductDTO> httpEntity = new HttpEntity<>(createProductDTO);
                    restTemplate.exchange(
                            updateUrl + productCartEntity.getProductId(),
                            HttpMethod.PUT,
                            httpEntity,
                            Void.class
                    );

                    totalPrice += restProduct.getBody().getPrice() * quantity;
                }
            }


        }

        cartEntity.setTotalPrice(totalPrice);
        cartEntity.setOrderedProducts(productCartEntities);
        cartRepository.save(cartEntity);


        cartDTO.setId(cartEntity.getId());
        cartDTO.setNumber(cart.getNumber());
        cartDTO.setTotalPrice(cartEntity.getTotalPrice());
        cartDTO.setProducts(productCartDTOList);

        return cartDTO;
    }

    @Override
    public CartDTO getCartById(UUID id) {
        if (id == null) {
            throw new ResourceBadRequestException(UUID_REQUIRED);
        }
        RestTemplate restTemplate = new RestTemplate();
        var cartEntity = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CART_NOT_FOUND));

        var orderedProducts = cartEntity.getOrderedProducts();
        List<ProductCartDTO> products = new ArrayList<>();

        for (ProductCartEntity productCartEntity : orderedProducts) {

            ResponseEntity<ProductDTO> responseEntity =
                    restTemplate.getForEntity(
                            searchUrl + productCartEntity.getProductId(),
                            ProductDTO.class);

            var productCart = new ProductCartDTO(
                    productCartEntity.getId(),
                    productCartEntity.getProductId(),
                    responseEntity.getBody().getName(),
                    productCartEntity.getOrderedQuantity()
            );

            products.add(productCart);

        }

        var cart = new CartDTO(
                cartEntity.getId(),
                cartEntity.getNumber(),
                cartEntity.getTotalPrice(),
                products
        );

        return cart;
    }

    @Override
    @Transactional
    public CartDTO updateCart(CreateCartDTO cart, UUID id) {
        return null;
    }

    @Override
    @Transactional
    public void deleteCart(UUID id) {
        cartRepository.deleteById(id);
    }
}
