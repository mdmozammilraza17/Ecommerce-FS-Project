package com.ecommerce.entity.refreshToken;

import com.ecommerce.entity.registration.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@Entity (name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String token;

    @Column (nullable = false)
    private Instant expiryDate;

    @Column(nullable = false)
    private boolean revoked = false;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id", nullable = false)
    private UserEntity userEntity;

    private Instant createdAt;
    private Instant updatedAt;
}
