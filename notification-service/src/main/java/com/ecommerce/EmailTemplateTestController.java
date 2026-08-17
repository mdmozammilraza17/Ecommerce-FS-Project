package com.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailTemplateTestController {

    @GetMapping("/test/otp-email")
    public String testOtpEmail(Model model) {

        model.addAttribute("customerName", "Mozammil");
        model.addAttribute("otp", "123456");

        return "otp-email";
    }
}