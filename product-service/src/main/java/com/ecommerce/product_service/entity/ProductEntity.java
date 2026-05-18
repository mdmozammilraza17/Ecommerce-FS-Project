package com.ecommerce.product_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity (name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column (nullable = false, length = 50)
    private String productName;

    @Column (nullable = false, length = 250)
    private String description;

    private BigDecimal discountPrice;

    @Column (nullable = false, length = 60)
    private String brand;

    @Column (nullable = false)
    private BigDecimal price;

    @Column (nullable = false)
    private Integer quantity;

    @Column (nullable = false)
    private Long categoryId;

    @Column (nullable = false)
    private boolean active;

    private String sku;

    @CreationTimestamp
    @Column (updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
