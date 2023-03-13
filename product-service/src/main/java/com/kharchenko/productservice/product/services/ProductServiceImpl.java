package com.kharchenko.productservice.product.services;

import com.kharchenko.productservice.exceptions.ResourceBadRequestException;
import com.kharchenko.productservice.exceptions.ResourceNotFoundException;
import com.kharchenko.productservice.product.entities.ProductEntity;
import com.kharchenko.productservice.product.mappers.ProductMapper;
import com.kharchenko.productservice.product.models.DTOs.CreateProductDTO;
import com.kharchenko.productservice.product.models.DTOs.FilterDTO;
import com.kharchenko.productservice.product.models.DTOs.ProductDTO;
import com.kharchenko.productservice.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {


    private static final String PRODUCT_NOT_FOUND = "Product was not found with id: ";

    private static final String FILTER_DATA_REQUIRED = "Can`t find Product without any filter";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productMapper.productEntitiesListToProductDTOList(productEntities);
    }

    @Override
    @Transactional
    public ProductDTO createProduct(CreateProductDTO product) {
        return productMapper.productEntityToProductDTO(
                productRepository.save(
                        productMapper.createProductDTOToProductEntity(product)));
    }


    @Override
    public ProductDTO getProductById(UUID id) {
        var productEntity = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND + id));

        return productMapper.productEntityToProductDTO(productEntity);
    }


    private ProductDTO getProductByNumber(Long number) {
        var productEntity = productRepository
                .findByNumber(number)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND + number));

        return productMapper.productEntityToProductDTO(productEntity);
    }

    @Override
    public ProductDTO getProductByFilter(FilterDTO filterDTO) {

        if (filterDTO.getId() != null) {
            var filter = filterDTO.getId();
            return getProductById(UUID.fromString(filter));

        } else if (filterDTO.getNumber() != null) {
            var filter = filterDTO.getNumber();
            return getProductByNumber(filter);
        } else {
            throw new ResourceBadRequestException(FILTER_DATA_REQUIRED);
        }

    }

    @Override
    @Transactional
    public ProductDTO updateProduct(CreateProductDTO createProductDTO, UUID id) {

        var productEntity = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND + id));

        productEntity.setNumber(createProductDTO.getNumber());
        productEntity.setName(createProductDTO.getName());
        productEntity.setDescription(createProductDTO.getDescription());
        productEntity.setType(createProductDTO.getType());
        productEntity.setUnit(createProductDTO.getUnit());
        productEntity.setQuantity(createProductDTO.getQuantity());
        productEntity.setPrice(createProductDTO.getPrice());
        productRepository.save(productEntity);

        return productMapper.productEntityToProductDTO(productEntity);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
