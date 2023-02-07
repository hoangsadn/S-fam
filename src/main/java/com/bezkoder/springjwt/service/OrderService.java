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

    public List<Order> getListOrders(String username){

        List<Order> orders = repository.findAllByUsername(username);

        return orders;
    }

    public List<Order> getAll(){
        return repository.findAll();
    }

    public String delOrder(Long id){
        repository.deleteById(id);
        return "del order success";
    }




    public Order getOrderById(Long id){

        Order order = repository.findFirstById(id).orElse(null);

        return order;
    }

    public Order insertOrder(Order req){
        repository.save(req);
        return repository.findFirstByUsername(req.getUsername()).orElse(null);
    }
}
