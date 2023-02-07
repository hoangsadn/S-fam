package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Order;
import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.repository.ReviewRepository;
import com.bezkoder.springjwt.service.OrderService;
import com.bezkoder.springjwt.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService service;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/s")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Review> allAccess() {
        LOGGER.info("all");
        return reviewRepository.findAll();
    }


    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> allAccesss() {
        LOGGER.info("all 2");
        return service.getListOrders();
    }

}
