package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.repository.ReviewRepository;
import com.bezkoder.springjwt.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/{id}/reviews")
    public List<Review> getAllReviews(@PathVariable("id") Long id) {
        LOGGER.info("review");
        return reviewRepository.findAllByProductId(id);
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        LOGGER.info("all products");

        return productService.getListProduct();
    }

    @GetMapping("/{id}")
    public Product getProductId(@PathVariable("id") int id) {
        LOGGER.info("Product id");
        return productService.getProductById(id);
    }


    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public Boolean saveProduct(@RequestBody Product res ) {
        LOGGER.info("Insert id");
        return productService.insertProduct(res);
    }

}
