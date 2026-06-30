package com.banner.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "banners")
public class BannerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (nullable = false, length = 100)
    private String title;

    @Column (nullable = false)
    private String imageUrl;

    @Column (nullable = false, unique = true)
    private String publicId;

    @Column (length = 500)
    private String redirectUrl;

    @Column (nullable = false)
    private Boolean active = true;

    @Column (nullable = false, unique = true)
    private Integer displayOrder;

    @CreationTimestamp
    @Column (updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
