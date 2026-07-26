package com.ecommerce.repository.registration;

import com.ecommerce.entity.registration.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmailAddress(String emailAddress);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findByEmailAddress(String emailAddress);

    Optional<UserEntity> findByEmailAddressOrPhoneNumber(
            String emailAddress, String phoneNumber);
}
