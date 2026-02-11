package com.ecommerce.product_service.controller;


import com.ecommerce.product_service.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    final OrderService orderService;


    public ProductController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping ("/get/product/info")
    public String getProduct ()
    {
        return "This is called by Product Controller: "+orderService.callUserService();
    }
}
