package com.ecommerce.product_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity (name = "product_table")
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

    @Column (nullable = false, length = 60)
    private String brand;

    @Column (nullable = false)
    private BigDecimal price;

    @Column (nullable = false)
    private Integer quantity;

    @Column (nullable = false)
    private String category;

    @Column (nullable = false)
    private String imageUrl;

    @Column (nullable = false)
    private boolean active;

    @CreationTimestamp
    @Column (updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
