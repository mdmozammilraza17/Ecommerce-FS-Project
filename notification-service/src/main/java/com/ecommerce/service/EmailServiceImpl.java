package com.ecommerce.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(
            JavaMailSender javaMailSender,
            TemplateEngine templateEngine
    ) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendOtpEmail(String firstName, String to, String otp) {

        try {

            // ==========================================
            // 1. Thymeleaf Context
            // ==========================================

            Context context = new Context();

            context.setVariable("customerName", firstName);
            context.setVariable("otp", otp);


            // ==========================================
            // 2. Process HTML Template
            // ==========================================

            String html = templateEngine.process(
                    "otp-email",
                    context
            );


            // ==========================================
            // 3. Create MIME Message
            // ==========================================

            MimeMessage mimeMessage =
                    javaMailSender.createMimeMessage();


            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            mimeMessage,
                            true,
                            "UTF-8"
                    );


            // ==========================================
            // 4. Sender
            // ==========================================

            helper.setFrom(
                    "stdgrocerystoredto@gmail.com",
                    "STD Grocery Store"
            );


            // ==========================================
            // 5. Receiver
            // ==========================================

            helper.setTo(to);


            // ==========================================
            // 6. Subject
            // ==========================================

            helper.setSubject(
                    "Registration Verification OTP - STD Grocery Store"
            );


            // ==========================================
            // 7. HTML Content
            // ==========================================

            helper.setText(
                    html,
                    true
            );


            // ==========================================
            // 8. Header Image
            // ==========================================

            helper.addInline(
                    "headerImage",
                    new ClassPathResource(
                            "static/email/email-header.png"
                    ),
                    "image/png"
            );


            // ==========================================
            // 9. Send Email
            // ==========================================

            javaMailSender.send(mimeMessage);


            System.out.println(
                    "OTP email sent successfully to: " + to
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to send OTP email",
                    e
            );
        }
    }

    @Override
    public void resendOtpEmail(String firstName, String to, String otp) {
        try {

            // ==========================================
            // 1. Thymeleaf Context
            // ==========================================

            Context context = new Context();

            context.setVariable("customerName", firstName);
            context.setVariable("otp", otp);


            // ==========================================
            // 2. Process Resend OTP HTML Template
            // ==========================================

            String html = templateEngine.process(
                    "otp-resend-topic",
                    context
            );


            // ==========================================
            // 3. Create MIME Message
            // ==========================================

            MimeMessage mimeMessage =
                    javaMailSender.createMimeMessage();


            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            mimeMessage,
                            true,
                            "UTF-8"
                    );


            // ==========================================
            // 4. Sender
            // ==========================================

            helper.setFrom(
                    "stdgrocerystoredto@gmail.com",
                    "STD Grocery Store"
            );


            // ==========================================
            // 5. Receiver
            // ==========================================

            helper.setTo(to);


            // ==========================================
            // 6. Subject
            // ==========================================

            helper.setSubject(
                    "Your New OTP - STD Grocery Store"
            );


            // ==========================================
            // 7. HTML Content
            // ==========================================

            helper.setText(
                    html,
                    true
            );


            // ==========================================
            // 8. Header Image
            // ==========================================

            helper.addInline(
                    "headerImage",
                    new ClassPathResource(
                            "static/email/email-header.png"
                    ),
                    "image/png"
            );


            // ==========================================
            // 9. Send Email
            // ==========================================

            javaMailSender.send(mimeMessage);


            System.out.println(
                    "Resend OTP email sent successfully to: " + to
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to send resend OTP email",
                    e
            );
        }
    }
}