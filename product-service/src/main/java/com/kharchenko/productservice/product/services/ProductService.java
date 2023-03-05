package com.kharchenko.productservice.product.services;

import com.kharchenko.productservice.product.models.DTOs.CreateProductDTO;
import com.kharchenko.productservice.product.models.DTOs.FilterDTO;
import com.kharchenko.productservice.product.models.DTOs.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(CreateProductDTO product);

    ProductDTO getProductByFilter(FilterDTO filter);

    ProductDTO updateProduct(CreateProductDTO product, UUID id);

    void deleteProduct(UUID id);
}
