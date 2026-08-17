package com.ecommerce.service.registration;

import com.ecommerce.dto.registration.SignupRequest;
import com.ecommerce.dto.registration.SignupResponse;
import com.ecommerce.dto.registration.VerifyOtpRequest;
import com.ecommerce.dto.registration.VerifyOtpResponse;
import com.ecommerce.entity.registration.UserEntity;
import com.ecommerce.enums.Role;
import com.ecommerce.enums.UserStatus;
import com.ecommerce.exception.registration.BadRequestException;
import com.ecommerce.exception.registration.ConflictException;
import com.ecommerce.exception.registration.ResourceNotFoundException;
import com.ecommerce.repository.registration.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailOtpService emailOtpService;

    public RegistrationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, EmailOtpService emailOtpService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.emailOtpService = emailOtpService;
    }

    // Create User or User Registration
    @Transactional
    @Override
    public SignupResponse signupUser(SignupRequest signupRequest) {

        // Validate password
        if (!signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
            throw new BadRequestException("Password and Confirm Password do not match");
        }

        // Check email exists or not
        Optional<UserEntity> byEmailAddress = userRepository.
                findByEmailAddress(signupRequest.getEmailAddress());

        if (byEmailAddress.isPresent())
        {
            UserEntity user = byEmailAddress.get();

            // If status as ACTIVE send a mesage in response
            if (user.getStatus() == UserStatus.ACTIVE)
            {
                throw new ConflictException("User already registered, pls log in");
            }

            user.setFirstName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setPhoneNumber(signupRequest.getPhoneNumber());

            user.setPassword(
                    passwordEncoder.encode(
                            signupRequest.getPassword()
                    )
            );

            userRepository.save(user);

            // If status as PENDING then generate and send new OTP
            emailOtpService.generateAndSaveOtp(user);

            SignupResponse signupResponse = new SignupResponse();
            signupResponse.setId(user.getId());
            signupResponse.setMessage("Your account is pending verification. A new OTP has been sent.");

            return signupResponse;

        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(signupRequest.getFirstName());
        userEntity.setLastName(signupRequest.getLastName());
        userEntity.setEmailAddress(signupRequest.getEmailAddress());
        userEntity.setPhoneNumber(signupRequest.getPhoneNumber());
        userEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userEntity.setStatus(UserStatus.PENDING);
        userEntity.setRole(Role.CUSTOMER);

        UserEntity savedUser = userRepository.save(userEntity);

        // save otp in DB
        emailOtpService.generateAndSaveOtp(savedUser);

        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setId(savedUser.getId());
        signupResponse.setMessage("Registration successful. Your account is pending verification. " +
                "An OTP has been sent to your registered email address.");
        return signupResponse;
    }

    @Override
    @Transactional
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest request) {

        // Get user
        UserEntity user = userRepository.findByEmailAddress(request.getEmail()).orElseThrow(() -> new
                ResourceNotFoundException("User not found!!"));

        // Call email OTP and check
        emailOtpService.verifyOtp(user, request.getOtp());

        // Make true and ACTIVE
        user.setEmailVerified(true);
        user.setStatus(UserStatus.ACTIVE);

        userRepository.save(user);

        VerifyOtpResponse verifyOtpResponse = new VerifyOtpResponse();
        verifyOtpResponse.setMessage("Account verified successfully. Your account is now active.");

        return verifyOtpResponse;
    }


}
