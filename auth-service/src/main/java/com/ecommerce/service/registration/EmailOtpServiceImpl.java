package com.ecommerce.service.registration;

import com.ecommerce.entity.registration.EmailOtp;
import com.ecommerce.entity.registration.UserEntity;
import com.ecommerce.exception.registration.BadRequestException;
import com.ecommerce.repository.registration.EmailOtpRepository;
import com.ecommerce.repository.registration.UserRepository;
import com.ecommerce.util.OtpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailOtpServiceImpl implements EmailOtpService {

    private final EmailOtpRepository emailOtpRepository;

    private final EmailService emailService;

    private final UserRepository userRepository;


    public EmailOtpServiceImpl(EmailOtpRepository emailOtpRepository, EmailService emailService,
                               UserRepository userRepository) {
        this.emailOtpRepository = emailOtpRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @Override
    public void generateAndSaveOtp(UserEntity user) {

        // Delete the OTP whose consumed will be false while create multiple time account
        emailOtpRepository.deleteByUserEntityAndConsumedFalse(user);

        EmailOtp emailOtp = new EmailOtp();
        emailOtp.setUserEntity(user);
        emailOtp.setOtp(OtpUtil.generateOtp());
        emailOtp.setConsumed(false);
        emailOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        emailOtpRepository.save(emailOtp);
        emailService.sendOtpEmail(user.getEmailAddress(), emailOtp.getOtp());
    }

    @Override
    @Transactional
    public void verifyOtp(UserEntity user, String otp) {

        EmailOtp emailOtp = emailOtpRepository
                .findByUserEntityAndConsumedFalse(user)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (emailOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("OTP has expired.");
        }

        if (!emailOtp.getOtp().equals(otp))
        {
            throw new BadRequestException("Invalid OTP");
        }

        emailOtp.setConsumed(true);

        emailOtpRepository.save(emailOtp);

    }
}
