package com.ecommerce.product_service.mapper;

import com.ecommerce.product_service.dto.ProductDTO;
import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // DTO -> Entity
    ProductEntity toEntity(ProductRequestDTO productRequestDTO);

    // Entity -> DTO
    ProductResponseDTO toDTO(ProductEntity productEntity);
}
