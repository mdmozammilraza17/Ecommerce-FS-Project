package com.ecommerce.product_service.mapper;

import com.ecommerce.product_service.dto.ProductDTO;
import com.ecommerce.product_service.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(ProductEntity entity);

    ProductEntity toEntity(ProductDTO dto);
}
