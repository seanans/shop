package com.kharchenko.productservice.product.controllers;

import com.kharchenko.productservice.product.models.DTOs.CreateProductDTO;
import com.kharchenko.productservice.product.models.DTOs.FilterDTO;
import com.kharchenko.productservice.product.models.DTOs.ProductDTO;
import com.kharchenko.productservice.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("")
    private List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("")
    private ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    private ResponseEntity<ProductDTO> updateProduct(@RequestParam(value = "id") UUID id, @RequestBody CreateProductDTO product) {
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/filter")
    private ResponseEntity<ProductDTO> getProductByFilter(@RequestBody FilterDTO filter) {
        return new ResponseEntity<>(productService.getProductByFilter(filter), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    private HttpStatus deleteProduct(@RequestParam(value = "id") UUID id) {
        productService.deleteProduct(id);
        return HttpStatus.ACCEPTED;
    }
}
