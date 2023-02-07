package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Order;
import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ReviewRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.service.OrderService;
import com.bezkoder.springjwt.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService service;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> allAccess() {

        LOGGER.info("find order");
        return service.getAll();
    }


    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public Order postOrder(Order order) {
        LOGGER.info("create order");
        return service.insertOrder(order);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Order getOrders(@PathVariable("id") Long id) {
        LOGGER.info("find all");

        return service.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delOrder(@PathVariable("id") Long id) {
        LOGGER.info("find all");

        return service.delOrder(id);
    }


    @GetMapping("/mine")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Order> getOrders(Authentication authentication) {
        LOGGER.info("find all");
        User user = jwtUtils.loadUserInfo(authentication);
        return service.getListOrders(user.getName());
    }


}
