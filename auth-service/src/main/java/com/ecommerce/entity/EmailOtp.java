package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "email_otp")
public class EmailOtp {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String otp;

    private LocalDateTime expiryTime;

    private boolean consumed = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "users_id", nullable = false)
    private UserEntity userEntity;
}
