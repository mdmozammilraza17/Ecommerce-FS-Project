package com.ecommerce.product_service.config;

import com.ecommerce.product_service.dto.CategoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "categories-service", configuration = FeignCategoryConfig.class)
public interface CategoryClient {

    @GetMapping("/api/categories/{id}")
    CategoryResponseDTO getCategoryById(@PathVariable("id") Long id);
}
