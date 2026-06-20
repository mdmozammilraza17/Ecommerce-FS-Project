package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.PageResponseDTO;
import com.ecommerce.product_service.dto.ProductDTO;
import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.exception.CategoryServiceUnavailableException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    // Create product
    public ProductResponseDTO createProduct (ProductRequestDTO productRequestDTO) throws CategoryServiceUnavailableException;

    // Get single product (with category)
    ProductResponseDTO getProductById(Long id);

    // Get all products
    PageResponseDTO<ProductResponseDTO> getAllProducts(int page, int size, String sortBy, String sortDir);
}
