package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Order;
import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.repository.OrderRepository;
import com.bezkoder.springjwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> getListOrders(){

        List<Order> orders = repository.findAll();

        return orders;
    }
}
