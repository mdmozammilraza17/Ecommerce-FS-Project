package com.ecommerce.entity;

import com.ecommerce.enums.Role;
import com.ecommerce.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 30, nullable = false)
    private String firstName;

    @Column (length = 25, nullable = false)
    private String lastName;

    @Column (length = 50, nullable = false, unique = true)
    private String emailAddress;

    @Column (length = 15, nullable = false, unique = true)
    private String phoneNumber;

    @Column (length = 60, nullable = false)
    private String password;

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailOtp> emailOtps = new ArrayList<>();
}
