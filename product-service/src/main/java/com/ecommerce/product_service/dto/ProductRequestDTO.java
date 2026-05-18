package com.ecommerce.product_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String productName;

    private String description;

    private String brand;

    private BigDecimal price;

    private BigDecimal discountPrice;

    private Integer quantity;

    private Long categoryId;

    private boolean active;

    private String sku;
}