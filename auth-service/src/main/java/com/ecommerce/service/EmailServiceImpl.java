package com.ecommerce.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendOtpEmail(String to, String otp) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom("stdgrocerystoredto@gmail.com", "STD Grocery Store");
            helper.setTo(to);
            helper.setSubject("Verify Your Email - STD Grocery Store");

            String html = """
                    <!DOCTYPE html>
                    <html>
                    <body style="margin:0;padding:40px;background:#F4F6F9;font-family:Arial,sans-serif;">

                    <table width="600" align="center" cellpadding="0" cellspacing="0"
                           style="background:#FFFFFF;border-radius:12px;overflow:hidden;box-shadow:0 2px 10px rgba(0,0,0,0.1);">

                        <!-- Header Image -->
                        <tr>
                            <td>
                                <img src="cid:headerImage"
                                     width="600"
                                     alt="STD Grocery Store"
                                     style="display:block;width:100%%;height:auto;">
                            </td>
                        </tr>
            
                        <!-- Body -->
                        <tr>
                            <td align="center" style="padding:40px;">

                                <div style="
                                        width:70px;
                                        height:70px;
                                        border-radius:50%%;
                                        background:#E8F5E9;
                                        line-height:70px;
                                        font-size:34px;">
                                    🛡️
                                </div>

                                <h2 style="color:#1F2937;margin-top:20px;">
                                    Verify Your Email
                                </h2>

                                <p style="
                                        color:#6B7280;
                                        font-size:16px;
                                        line-height:26px;">

                                    Thank you for registering with
                                    <strong>STD Grocery Store</strong>.

                                    <br><br>

                                    Please use the OTP below to activate your account.

                                </p>

                                <div style="
                                        margin:30px auto;
                                        width:260px;
                                        background:#E8F5E9;
                                        border:2px dashed #16A34A;
                                        border-radius:12px;
                                        padding:20px;
                                        font-size:40px;
                                        font-weight:bold;
                                        letter-spacing:10px;
                                        color:#16A34A;">

                                    %s

                                </div>

                                <table width="100%%"
                                       cellpadding="15"
                                       style="
                                       background:#F0FDF4;
                                       border-left:5px solid #16A34A;
                                       border-radius:8px;">

                                    <tr>

                                        <td>

                                            <strong style="color:#166534;">
                                                Secure Verification
                                            </strong>

                                            <p style="margin:10px 0;color:#6B7280;">
                                                Your OTP is valid for
                                                <strong>5 minutes</strong>.
                                            </p>

                                            <p style="margin:0;color:#DC2626;">
                                                Never share this OTP with anyone.
                                            </p>

                                        </td>

                                    </tr>

                                </table>

                            </td>
                        </tr>

                        <!-- Footer -->
                        <tr>

                            <td align="center"
                                style="background:#F9FAFB;padding:25px;color:#9CA3AF;font-size:13px;">

                                © 2026 STD Grocery Store

                                <br><br>

                                Fresh Products • Fast Delivery • Secure Shopping

                            </td>

                        </tr>

                    </table>

                    </body>
                    </html>
                    """.formatted(otp);

            helper.setText(html, true);

            helper.addInline(
                    "headerImage",
                    new ClassPathResource("static/email/email-header.png"),
                    "image/png"
            );

            javaMailSender.send(mimeMessage);

            System.out.println("Email sent successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to send OTP email", e);
        }
    }
}