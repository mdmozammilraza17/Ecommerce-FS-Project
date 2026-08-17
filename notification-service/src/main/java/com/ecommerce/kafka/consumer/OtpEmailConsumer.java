package com.ecommerce.kafka.consumer;

import com.ecommerce.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OtpEmailConsumer {

    private final EmailService emailService;

    public OtpEmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(
            topics = "otp-email-topic",
            groupId = "notification-service"
    )
    public void consumeOtpEmailEvent(com.ecommerce.event.OtpEmailEvent event) {

        emailService.sendOtpEmail(
                event.getFirstName().toString(),
                event.getEmail().toString(),
                event.getOtp().toString()
        );
    }
}
