package com.kharchenko.productservice.product.mappers;

import com.kharchenko.productservice.product.entities.ProductEntity;
import com.kharchenko.productservice.product.models.DTOs.CreateProductDTO;
import com.kharchenko.productservice.product.models.DTOs.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {

    ProductEntity createProductDTOToProductEntity(CreateProductDTO product);

    ProductDTO productEntityToProductDTO(ProductEntity product);

    List<ProductDTO> productEntitiesListToProductDTOList(List<ProductEntity> productEntities);
}
