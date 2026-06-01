package com.ecommerce.payment_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class TestPayment {

    @GetMapping("/test")
    public String testPayment ()
    {
        return "Test Payment";
    }
}
