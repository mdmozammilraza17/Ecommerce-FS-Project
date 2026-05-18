package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductDTO;
import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    // Create product
    public ProductResponseDTO createProduct (ProductRequestDTO productRequestDTO);

    // Get single product (with category)
    ProductResponseDTO getProductById(Long id);

    // Get all products
    List<ProductResponseDTO> getAllProducts();
}
