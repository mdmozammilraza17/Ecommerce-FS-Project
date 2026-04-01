package com.ecommerce.product_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String productName;

    private String description;

    private String brand;

    private BigDecimal price;

    private Integer quantity;

    private String category;

    private String imageUrl;

}
