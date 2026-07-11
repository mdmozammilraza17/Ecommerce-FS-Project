package com.ecommerce.repository;

import com.ecommerce.entity.EmailOtp;
import com.ecommerce.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailOtpRepository extends JpaRepository<EmailOtp, Long> {

    Optional<EmailOtp> findByUserEntity(UserEntity userEntity);

    Optional<EmailOtp> findByUserEntityAndConsumedFalse(UserEntity userEntity);

    void deleteByUserEntityAndConsumedFalse(UserEntity user);
}
