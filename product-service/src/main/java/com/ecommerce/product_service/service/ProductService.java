package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductService {

    public ProductDTO createProduct (ProductDTO productDTO);
}
