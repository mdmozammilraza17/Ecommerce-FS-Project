package com.ecommerce.product_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long productId;

    private String productName;

    private String description;

    private String brand;

    private BigDecimal price;

    private BigDecimal discountPrice;

    private Integer quantity;

    private boolean active;

    private String sku;

    private CategoryResponseDTO categoryResponseDTO;
}