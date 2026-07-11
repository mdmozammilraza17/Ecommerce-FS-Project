package com.ecommerce.repository;

import com.ecommerce.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmailAddress(String emailAddress);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findByEmailAddress(String emailAddress);
}
